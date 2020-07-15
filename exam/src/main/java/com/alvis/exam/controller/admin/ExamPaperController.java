package com.alvis.exam.controller.admin;

import cn.hutool.core.util.StrUtil;
import com.alvis.exam.base.BaseApiController;
import com.alvis.exam.base.RestResponse;
import com.alvis.exam.domain.*;
import com.alvis.exam.service.*;
import com.alvis.exam.utility.DateTimeUtil;
import com.alvis.exam.utility.PageInfoHelper;
import com.alvis.exam.utility.YamlUtil;
import com.alvis.exam.viewmodel.admin.exam.ExamPaperEditRequestVM;
import com.alvis.exam.viewmodel.admin.exam.ExamPaperPageRequestVM;
import com.alvis.exam.viewmodel.admin.exam.ExamResponseVM;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFStyles;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController("AdminExamPaperController")
@RequestMapping(value = "/api/admin/exam/paper")
public class ExamPaperController extends BaseApiController {

    private static CTStyles wordStyles = null;

    @Value("${filePath}")
    private String filePath;

    @Autowired
    ITSubjectService subjectService;

    @Autowired
    ITExamPaperService texamPaperService;

    @Autowired
    ITQuestionService questionService;

    @Autowired
    ITTextContentService textContentService;

    @Autowired
    ExamPaperService examPaperService;

    private static List<String> choose = new ArrayList<>(Arrays.asList("A", "B", "C","D"));

    /**
     * Word整体样式
     */
    static {
        Map<?, ?> map = null;
        try {
            map = YamlUtil.loadYaml("application.yml");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String value = String.valueOf(YamlUtil.getProperty(map, "filePath"));
        XWPFDocument template;
        try {
            // 读取模板文档
            template = new XWPFDocument(new FileInputStream(value + "test.docx"));
            // 获得模板文档的整体样式
            wordStyles = template.getStyle();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String a = "1.2.3";
        String[] split = a.split("\\.");
        System.out.println(split[2]);
        /*Map<?, ?> map = YamlUtil.loadYaml("application.yml");
        String value = String.valueOf(YamlUtil.getProperty(map, "filePath"));
        System.out.println(value);*/
    }
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public RestResponse<PageInfo<ExamResponseVM>> pageList(@RequestBody ExamPaperPageRequestVM model) {
        PageInfo<ExamPaper> pageInfo = examPaperService.page(model);
        PageInfo<ExamResponseVM> page = PageInfoHelper.copyMap(pageInfo, e -> {
            ExamResponseVM vm = modelMapper.map(e, ExamResponseVM.class);
            vm.setCreateTime(DateTimeUtil.dateFormat(e.getCreateTime()));
            return vm;
        });
        return RestResponse.ok(page);
    }

    @RequestMapping(value = "/taskExamPage", method = RequestMethod.POST)
    public RestResponse<PageInfo<ExamResponseVM>> taskExamPageList(@RequestBody ExamPaperPageRequestVM model) {
        PageInfo<ExamPaper> pageInfo = examPaperService.taskExamPage(model);
        PageInfo<ExamResponseVM> page = PageInfoHelper.copyMap(pageInfo, e -> {
            ExamResponseVM vm = modelMapper.map(e, ExamResponseVM.class);
            vm.setCreateTime(DateTimeUtil.dateFormat(e.getCreateTime()));
            return vm;
        });
        return RestResponse.ok(page);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public RestResponse edit(@RequestBody TSubject subject) {
        /*ExamPaper examPaper = examPaperService.savePaperFromVM(model, getCurrentUser());
        ExamPaperEditRequestVM newVM = examPaperService.examPaperToVM(examPaper.getId());*/
        subjectService.updateById(subject);

        /*TExamPaper texamPaper = new TExamPaper();
        texamPaper.setSubjectId(tSubject.getId());
        texamPaper.setName(model.getPaperName());

        texamPaperService.save(texamPaper);*/
        return RestResponse.ok();
    }

    @RequestMapping(value = "/select/{id}", method = RequestMethod.POST)
    public RestResponse<ExamPaperEditRequestVM> select(@PathVariable Integer id) {
        ExamPaperEditRequestVM vm = examPaperService.examPaperToVM(id);
        return RestResponse.ok(vm);
    }

    @RequestMapping(value = "/select/", method = RequestMethod.POST)
    public List<TExamPaper> select() {
        List<TExamPaper> list = texamPaperService.list(new LambdaUpdateWrapper<TExamPaper>().eq(TExamPaper::getDeleted,"0"));
        return list;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public RestResponse<ExamPaperEditRequestVM> insert(@RequestBody TExamPaper texamPaper) {
        texamPaperService.save(texamPaper);
        return RestResponse.ok();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public RestResponse delete(@PathVariable Integer id) {
        subjectService.update(new LambdaUpdateWrapper<TSubject>().set(TSubject::getDeleted,"1").eq(TSubject::getId,id));
        /*ExamPaper examPaper = examPaperService.selectById(id);
        examPaper.setDeleted(true);
        examPaperService.updateByIdFilter(examPaper);*/
        return RestResponse.ok();
    }

    @RequestMapping(value = "/deletebatch/{ids}", method = RequestMethod.POST)
    public RestResponse deletebatch(@PathVariable String ids) {
        List<String> list = new ArrayList(Arrays.asList(ids.split(",")));
        for (String str : list) {
            int i = Integer.parseInt(str);
            subjectService.update(new LambdaUpdateWrapper<TSubject>().set(TSubject::getDeleted,"1").eq(TSubject::getId,i));
            texamPaperService.update(new LambdaUpdateWrapper<TExamPaper>().set(TExamPaper::getDeleted,"1").eq(TExamPaper::getSubjectId,i));
            questionService.update(new LambdaUpdateWrapper<TQuestion>().set(TQuestion::getDeleted,"1").eq(TQuestion::getSubjectId,i));
        }
        return RestResponse.ok();
    }

    public static List<String> changeWord(String path) throws IOException{
        InputStream is = new FileInputStream(path);
        List<String> list = new ArrayList<String>();
        XWPFDocument doc = new XWPFDocument(is);
        List<XWPFParagraph> paras = doc.getParagraphs();
        for (XWPFParagraph para : paras) {
            String text = para.getParagraphText();
            list.add(text);
        }
        return list;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public RestResponse upload(MultipartFile file,@RequestParam Map map) throws IOException {
        String subjectId = map.get("subjectId").toString();
        String supplier = ""; //map.get("supplier").toString();
        File newFile = new File(filePath);
        if (!newFile.exists()) {
            newFile.mkdirs();
        }

        String originalFilename = file.getOriginalFilename();
        String allpath = filePath + originalFilename;
        File newFile1 = new File(allpath);

        file.transferTo(newFile1);

        List<String> wordTitles = changeWord(allpath);

        XWPFDocument doc = new XWPFDocument();
        // 获取新建文档对象的样式
        XWPFStyles newStyles = doc.createStyles();
        // 关键行// 修改设置文档样式为静态块中读取到的样式
        newStyles.setStyles(wordStyles);
        int subject = 0;
        int examPaper = 0;
        int question = 0;
        int textContent = 0;
        for (String wordTitle : wordTitles) {
            if (StringUtils.isBlank(wordTitle)){
                continue;
            }
            int count = 0;
            if(StringUtils.isNumeric(wordTitle.trim().charAt(0)+"")){
                count = StrUtil.count(wordTitle.substring(0,8), ".");
            }

            if(count == 1){
                textContent = 0;
                XWPFParagraph para3 = doc.createParagraph();
                para3.setStyle("3");
                XWPFRun run3 = para3.createRun();
                question += 1;
                run3.setText(wordTitle+"["+subject+","+examPaper+","+question+"]");
            }else if(count == 2){
                XWPFParagraph para4 = doc.createParagraph();
                para4.setStyle("4");
                XWPFRun run4 = para4.createRun();

                textContent += 1;
                run4.setText(wordTitle+"["+subject+","+examPaper+","+question+","+textContent+"]");
            }else if (wordTitle.contains("A、") || wordTitle.contains("B、") || wordTitle.contains("C、") || wordTitle.contains("D、")|| wordTitle.contains("答案：")) {
                XWPFParagraph paraX = doc.createParagraph();
                XWPFRun runX = paraX.createRun();
                runX.setText(wordTitle+"["+subject+","+examPaper+","+question+","+textContent+"]");
            }
        }

        // word写入到文件
        FileOutputStream fos = new FileOutputStream(allpath);
        doc.write(fos);
        fos.close();

        getWordTitles(allpath,subjectId);
        datahandle();
        return RestResponse.ok();
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @GetMapping("/word")
    public void getWordTitles(String path,String subjectId) throws IOException {
        //String path= "D:/myDoc.docx";
        InputStream is = new FileInputStream(path);

        XWPFDocument doc = new XWPFDocument(is);
        List<XWPFParagraph> paras = doc.getParagraphs();

        String contentAll = "";

        List<TTextContent> insertTTextContentlist = new ArrayList<>();
        for (XWPFParagraph graph : paras) {
            String text = graph.getParagraphText();
            text = text.trim();
            if (StringUtils.isBlank(text)){
                continue;
            }

            String style = graph.getStyle();
            String flag = StringUtils.substringBetween(text,"[","]");
            String content = StringUtils.substringBefore(text,"[");

            if ("3".equals(style)) {
                //System.out.println(text+"--["+style+"]");
            }else if ("4".equals(style)) {
                content = content.substring(5);
                TQuestion tQuestion = new TQuestion();
                tQuestion.setName(content);
                tQuestion.setFlag(flag);
                tQuestion.setSubjectId(Integer.parseInt(subjectId));

                questionService.insertData(tQuestion);
                //insertTQuestionlist.add(tQuestion);
                //System.out.println(text+"--["+style+"]");
            }else {
                TTextContent textContent = new TTextContent();
                if (!content.contains("答案：")) {
                    contentAll += content;
                }else {
                    String answer = StringUtils.substringAfter(content, "答案：");
                    textContent.setChoose(contentAll);
                    textContent.setAnswer(answer);
                    textContent.setFlag(flag);
                    insertTTextContentlist.add(textContent);
                    contentAll = "";
                }
            }
        }

        //questionService.saveBatch(insertTQuestionlist);
        textContentService.saveBatch(insertTTextContentlist);

        List<TQuestion> questionlist = questionService.list();
        textContentService.updateQuestion(questionlist);

        List<TQuestion> questionlist1 = new ArrayList<>();
        List<TTextContent> textContentlist = textContentService.list();
        for (TTextContent tTextContent : textContentlist) {
            TQuestion TQuestion = new TQuestion();
            TQuestion.setId(tTextContent.getId());
            TQuestion.setInfoTextContentId(tTextContent.getId());
            TQuestion.setCorrect(tTextContent.getAnswer());
            if(!choose.contains(tTextContent.getAnswer())){
                TQuestion.setQuestionType("3");
            }
            questionlist1.add(TQuestion);

        }
        questionService.updateBatchById(questionlist1);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @GetMapping("/datahandle")
    public String datahandle() {

        List<TTextContent> textContentlist = textContentService.list();
        List<TTextContent> textContentlist1 = new ArrayList<>();
        for (TTextContent textContent : textContentlist) {
            TTextContent tTextContent = new TTextContent();
            tTextContent.setId(textContent.getId());

            String choose = textContent.getChoose();
            String A = StringUtils.substringBetween(choose,"A、","B、");
            String B = StringUtils.substringBetween(choose,"B、","C、");
            String C = StringUtils.substringBetween(choose,"C、","D、");
            String D = StringUtils.substringAfter(choose,"D、");

            String name = textContent.getQuestion();
            if(name.contains("\"")){
                name = name.replace("\"","\\"+"\"");
            }

            if (choose.contains(textContent.getAnswer())) {
                String one = "{\"titleContent\":\"<span style=\\\"font-family: 仿宋;\\\">" + name +"</span>\",\"analyze\":\"1\",\"questionItemObjects\":[";
                String aa = "{\"prefix\":\"A\",\"content\":\"<span style=\\\"font-family: 仿宋;\\\">";
                String bb = "{\"prefix\":\"B\",\"content\":\"<span style=\\\"font-family: 仿宋;\\\">";
                String cc = "{\"prefix\":\"C\",\"content\":\"<span style=\\\"font-family: 仿宋;\\\">";
                String dd = "{\"prefix\":\"D\",\"content\":\"<span style=\\\"font-family: 仿宋;\\\">";
                String common = "</span>\",\"score\":null},";
                String all = one +  aa + A + common+
                        bb + B + common+
                        cc + C + common+
                        dd + D + "</span>\",\"score\":null}],";
                String aaa = "\"correct\":\"";
                String bbb = "\"}";

                String allall = all + aaa + textContent.getAnswer() + bbb;
                tTextContent.setContent(allall);
            }else {
                String one = "{\"titleContent\":\"" + name +"\",\"analyze\":\"1\",\"questionItemObjects\":[";
                String aa = "{\"prefix\":\"A\",\"content\":\"是\",\"score\":null},";
                String bb = "{\"prefix\":\"B\",\"content\":\"否\",\"score\":null}";
                String cc = "],\"correct\":\"";
                String dd = "\"}";
                String all = one +  aa + bb + cc + textContent.getAnswer() + dd;

                tTextContent.setContent(all);
            }
            textContentlist1.add(tTextContent);
        }
        textContentService.updateBatchById(textContentlist1);
        return "";
    }
}
