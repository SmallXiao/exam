package com.alvis.exam.service.impl;

import com.alvis.exam.domain.other.KeyValue;
import com.alvis.exam.domain.Question;
import com.alvis.exam.domain.TextContent;
import com.alvis.exam.domain.enums.QuestionStatusEnum;
import com.alvis.exam.domain.enums.QuestionTypeEnum;
import com.alvis.exam.domain.question.QuestionItemObject;
import com.alvis.exam.domain.question.QuestionObject;
import com.alvis.exam.repository.QuestionMapper;
import com.alvis.exam.service.QuestionService;
import com.alvis.exam.service.SubjectService;
import com.alvis.exam.service.TextContentService;
import com.alvis.exam.utility.DateTimeUtil;
import com.alvis.exam.utility.JsonUtil;
import com.alvis.exam.utility.ModelMapperSingle;
import com.alvis.exam.utility.ExamUtil;
import com.alvis.exam.viewmodel.admin.question.QuestionEditItemVM;
import com.alvis.exam.viewmodel.admin.question.QuestionEditRequestVM;
import com.alvis.exam.viewmodel.admin.question.QuestionPageRequestVM;
import com.alvis.exam.viewmodel.admin.question.QuestionResponseVM;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl extends BaseServiceImpl<Question> implements QuestionService {

    protected final static ModelMapper modelMapper = ModelMapperSingle.Instance();
    private final QuestionMapper questionMapper;
    private final TextContentService textContentService;
    private final SubjectService subjectService;

    @Autowired
    public QuestionServiceImpl(QuestionMapper questionMapper, TextContentService textContentService, SubjectService subjectService) {
        super(questionMapper);
        this.textContentService = textContentService;
        this.questionMapper = questionMapper;
        this.subjectService = subjectService;
    }

    @Override
    public PageInfo<QuestionResponseVM> page(QuestionPageRequestVM requestVM) {
        return PageHelper.startPage(requestVM.getPageIndex(), requestVM.getPageSize(), "q.id desc").doSelectPageInfo(() ->
                questionMapper.page(requestVM)
        );
    }

    @Override
    public PageInfo<QuestionResponseVM> questionpage(QuestionPageRequestVM requestVM) {
        return PageHelper.startPage(requestVM.getPageIndex(), requestVM.getPageSize(), "id desc").doSelectPageInfo(() ->
                questionMapper.questionpage(requestVM)
        );
    }

    @Override
    public int getCount(Integer id) {
        return questionMapper.getCount(id);
    }

    @Override
    @Transactional
    public Question insertFullQuestion(QuestionEditRequestVM model, Integer userId) {

        Date now = new Date();
        Integer gradeLevel = subjectService.levelBySubjectId(model.getSubjectId());

        //题干、解析、选项等 插入
        TextContent infoTextContent = new TextContent();
        infoTextContent.setCreateTime(now);
        infoTextContent.setQuestion(model.getTitle());
        setQuestionInfoFromVM(infoTextContent, model);
        textContentService.insertByFilter(infoTextContent);

        Question question = new Question();
        question.setSubjectId(model.getSubjectId());
        if (!StringUtils.isEmpty(model.getPaperId())) {
            String paperId = model.getPaperId();
            String paperName = paperId.split("&")[1];
            paperId = paperId.split("&")[0];

            question.setPaperId(Integer.parseInt(paperId));
            question.setName(paperName);
        }
        question.setGradeLevel(gradeLevel);
        question.setCreateTime(now);
        question.setQuestionType(model.getQuestionType());
        question.setStatus(QuestionStatusEnum.OK.getCode());
        question.setScore(1);
        if(model.getQuestionType().toString().equals("3")){
            if(model.getCorrect().equals("A")){
                question.setCorrect("正确");
            }else {
                question.setCorrect("错误");
            }
        } else {
            question.setCorrect(model.getCorrect());
        }
        question.setDifficult(model.getDifficult());
        question.setInfoTextContentId(infoTextContent.getId());
        question.setCreateUser(userId);
        question.setDeleted(false);
        question.setName(model.getTitle());
        questionMapper.insertSelective(question);
        return question;
    }

    @Override
    @Transactional
    public Question updateFullQuestion(QuestionEditRequestVM model) {

        Question question = questionMapper.selectByPrimaryKey(model.getId());
        if (!StringUtils.isEmpty(model.getPaperId())) {
            String paperId = model.getPaperId();
            paperId = paperId.split("&")[0];
            question.setPaperId(Integer.parseInt(paperId));

            String paperName = paperId.split("&")[1];
            question.setName(paperName);
        }
        question.setSubjectId(model.getSubjectId());
//        Integer gradeLevel = subjectService.levelBySubjectId(model.getSubjectId());

        questionMapper.updateByPrimaryKeySelective(question);
        //题干、解析、选项等 更新
        TextContent infoTextContent = textContentService.selectById(question.getInfoTextContentId());
        setQuestionInfoFromVM(infoTextContent, model);
        textContentService.updateByIdFilter(infoTextContent);

        return question;
    }

    @Override
    public QuestionEditRequestVM getQuestionEditRequestVM(Integer questionId) {
        //题目映射
        Question question = questionMapper.selectByPrimaryKey(questionId);
        return getQuestionEditRequestVM(question);
    }

    @Override
    public QuestionEditRequestVM getQuestionEditRequestVM(Question question) {
        //题目映射
        TextContent questionInfoTextContent = textContentService.selectById(question.getInfoTextContentId());
        QuestionObject questionObject = JsonUtil.toJsonObject(questionInfoTextContent.getContent(), QuestionObject.class);
        QuestionEditRequestVM questionEditRequestVM = modelMapper.map(question, QuestionEditRequestVM.class);
        questionEditRequestVM.setTitle(questionObject.getTitleContent());

        //答案
        QuestionTypeEnum questionTypeEnum = QuestionTypeEnum.fromCode(question.getQuestionType());
        switch (questionTypeEnum) {
            case SingleChoice:
                questionEditRequestVM.setCorrect(questionObject.getCorrect());
                break;
            case TrueFalse:
                if (questionObject.getCorrect().contains("正确")) {
                    questionEditRequestVM.setCorrect("A");
                }else {
                    questionEditRequestVM.setCorrect("B");
                }
                //questionEditRequestVM.setCorrect(questionObject.getCorrect());
                break;
            case MultipleChoice:
                questionEditRequestVM.setCorrectArray(ExamUtil.contentToArray(questionObject.getCorrect()));
                break;
            case GapFilling:
                List<String> correctContent = questionObject.getQuestionItemObjects().stream().map(d -> d.getContent()).collect(Collectors.toList());
                questionEditRequestVM.setCorrectArray(correctContent);
                break;
            case ShortAnswer:
                questionEditRequestVM.setCorrect(questionObject.getCorrect());
                break;
            default:
                break;
        }
        questionEditRequestVM.setScore(ExamUtil.scoreToVM(question.getScore()));
        questionEditRequestVM.setAnalyze(questionObject.getAnalyze());


        //题目项映射
        List<QuestionEditItemVM> editItems = questionObject.getQuestionItemObjects().stream().map(o -> {
            QuestionEditItemVM questionEditItemVM = modelMapper.map(o, QuestionEditItemVM.class);
            if (o.getScore() != null) {
                questionEditItemVM.setScore(ExamUtil.scoreToVM(o.getScore()));
            }
            return questionEditItemVM;
        }).collect(Collectors.toList());
        questionEditRequestVM.setItems(editItems);
        return questionEditRequestVM;
    }

    public void setQuestionInfoFromVM(TextContent infoTextContent, QuestionEditRequestVM model) {

        StringBuilder choose = new StringBuilder(128);
        List<QuestionItemObject> itemObjects = model.getItems().stream().map(i ->
                {
                    QuestionItemObject item = new QuestionItemObject();
                    item.setPrefix(i.getPrefix());
                    item.setContent(i.getContent());
                    item.setScore(ExamUtil.scoreFromVM(i.getScore()));

                    String content = i.getContent();
                    if (i.getContent().contains(">") && i.getContent().split(">").length > 1) {
                        content = i.getContent().split(">")[1].split("<")[0];
                    }
                    choose.append(i.getPrefix()).append("、").append(content);
                    return item;
                }
        ).collect(Collectors.toList());

        String title = model.getTitle();
        if (title.contains(">") && title.split(">").length > 1) {
            title = model.getTitle().split(">")[1].split("<")[0];
        }

        QuestionObject questionObject = new QuestionObject();
        questionObject.setQuestionItemObjects(itemObjects);
        questionObject.setAnalyze(model.getAnalyze());
        questionObject.setTitleContent(title);
        questionObject.setCorrect(model.getCorrect());

        infoTextContent.setQuestion(title);
        infoTextContent.setChoose(choose.toString());
        infoTextContent.setAnswer(model.getCorrect());
        infoTextContent.setContent(JsonUtil.toJsonStr(questionObject));
    }

    @Override
    public Integer selectAllCount() {
        return questionMapper.selectAllCount();
    }

    @Override
    public List<Integer> selectMothCount() {
        Date startTime = DateTimeUtil.getMonthStartDay();
        Date endTime = DateTimeUtil.getMonthEndDay();
        List<String> mothStartToNowFormat = DateTimeUtil.MothStartToNowFormat();
        List<KeyValue> mouthCount = questionMapper.selectCountByDate(startTime, endTime);
        return mothStartToNowFormat.stream().map(md -> {
            KeyValue keyValue = mouthCount.stream().filter(kv -> kv.getName().equals(md)).findAny().orElse(null);
            return null == keyValue ? 0 : keyValue.getValue();
        }).collect(Collectors.toList());
    }

    @Override
    public List<QuestionResponseVM> getQuestionList(QuestionPageRequestVM model) {
        return questionMapper.getQuestionList(model);
    }
}
