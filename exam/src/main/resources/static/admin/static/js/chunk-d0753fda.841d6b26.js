(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-d0753fda"],{"0798":function(e,t,i){"use strict";i.r(t);var n=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",{staticClass:"app-container"},[i("el-form",{directives:[{name:"loading",rawName:"v-loading",value:e.formLoading,expression:"formLoading"}],ref:"form",attrs:{model:e.form,"label-width":"100px",rules:e.rules}},[i("el-form-item",{attrs:{label:"年级：",prop:"gradeLevel",required:""}},[i("el-select",{attrs:{placeholder:"年级",clearable:""},on:{change:e.levelChange},model:{value:e.form.gradeLevel,callback:function(t){e.$set(e.form,"gradeLevel",t)},expression:"form.gradeLevel"}},e._l(e.levelEnum,(function(e){return i("el-option",{key:e.key,attrs:{value:e.key,label:e.value}})})),1)],1),i("el-form-item",{attrs:{label:"学科：",prop:"subjectId",required:""}},[i("el-select",{attrs:{placeholder:"学科"},model:{value:e.form.subjectId,callback:function(t){e.$set(e.form,"subjectId",t)},expression:"form.subjectId"}},e._l(e.subjectFilter,(function(e){return i("el-option",{key:e.id,attrs:{value:e.id,label:e.name+" ( "+e.levelName+" )"}})})),1)],1),i("el-form-item",{attrs:{label:"题干：",prop:"title",required:""}},[i("el-input",{on:{focus:function(t){return e.inputClick(e.form,"title")}},model:{value:e.form.title,callback:function(t){e.$set(e.form,"title",t)},expression:"form.title"}})],1),i("el-form-item",{attrs:{label:"选项：",required:""}},e._l(e.form.items,(function(t,n){return i("el-form-item",{key:t.prefix,staticClass:"question-item-label",attrs:{label:t.prefix,"label-width":"50px"}},[i("el-input",{staticStyle:{width:"50px"},model:{value:t.prefix,callback:function(i){e.$set(t,"prefix",i)},expression:"item.prefix"}}),i("el-input",{staticClass:"question-item-content-input",on:{focus:function(i){return e.inputClick(t,"content")}},model:{value:t.content,callback:function(i){e.$set(t,"content",i)},expression:"item.content"}}),i("el-button",{staticClass:"question-item-remove",attrs:{type:"danger",size:"mini",icon:"el-icon-delete"},on:{click:function(t){return e.questionItemRemove(n)}}})],1)})),1),i("el-form-item",{attrs:{label:"解析：",prop:"analyze",required:""}},[i("el-input",{on:{focus:function(t){return e.inputClick(e.form,"analyze")}},model:{value:e.form.analyze,callback:function(t){e.$set(e.form,"analyze",t)},expression:"form.analyze"}})],1),i("el-form-item",{attrs:{label:"分数：",prop:"score",required:""}},[i("el-input-number",{attrs:{precision:1,step:1,max:100},model:{value:e.form.score,callback:function(t){e.$set(e.form,"score",t)},expression:"form.score"}})],1),i("el-form-item",{attrs:{label:"难度：",required:""}},[i("el-rate",{staticClass:"question-item-rate",model:{value:e.form.difficult,callback:function(t){e.$set(e.form,"difficult",t)},expression:"form.difficult"}})],1),i("el-form-item",{attrs:{label:"正确答案：",prop:"correct",required:""}},[i("el-radio-group",{model:{value:e.form.correct,callback:function(t){e.$set(e.form,"correct",t)},expression:"form.correct"}},e._l(e.form.items,(function(t){return i("el-radio",{key:t.prefix,attrs:{label:t.prefix}},[e._v(e._s(t.prefix))])})),1)],1),i("el-form-item",[i("el-button",{attrs:{type:"primary"},on:{click:e.submitForm}},[e._v("提交")]),i("el-button",{on:{click:e.resetForm}},[e._v("重置")]),i("el-button",{attrs:{type:"success"},on:{click:e.questionItemAdd}},[e._v("添加选项")]),i("el-button",{attrs:{type:"success"},on:{click:e.showQuestion}},[e._v("预览")])],1)],1),i("el-dialog",{staticStyle:{width:"100%",height:"100%"},attrs:{visible:e.richEditor.dialogVisible,"append-to-body":"","close-on-click-modal":!1,"show-close":!1,center:""},on:{"update:visible":function(t){return e.$set(e.richEditor,"dialogVisible",t)}}},[i("Ueditor",{on:{ready:e.editorReady}}),i("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[i("el-button",{attrs:{type:"primary"},on:{click:e.editorConfirm}},[e._v("确 定")]),i("el-button",{on:{click:function(t){e.richEditor.dialogVisible=!1}}},[e._v("取 消")])],1)],1),i("el-dialog",{staticStyle:{width:"100%",height:"100%"},attrs:{visible:e.questionShow.dialog},on:{"update:visible":function(t){return e.$set(e.questionShow,"dialog",t)}}},[i("QuestionShow",{attrs:{qType:e.questionShow.qType,question:e.questionShow.question,qLoading:e.questionShow.loading}})],1)],1)},r=[],o=(i("a4d3"),i("4de4"),i("4160"),i("a434"),i("e439"),i("dbb4"),i("b64b"),i("159b"),i("ade3")),s=i("294d"),a=i("63f4"),l=i("2f62"),c=i("2ac8");function u(e,t){var i=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),i.push.apply(i,n)}return i}function d(e){for(var t=1;t<arguments.length;t++){var i=null!=arguments[t]?arguments[t]:{};t%2?u(Object(i),!0).forEach((function(t){Object(o["a"])(e,t,i[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(i)):u(Object(i)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(i,t))}))}return e}var m={components:{Ueditor:a["a"],QuestionShow:s["a"]},data:function(){return{form:{id:null,questionType:1,gradeLevel:null,subjectId:null,title:"",items:[{prefix:"A",content:""},{prefix:"B",content:""},{prefix:"C",content:""},{prefix:"D",content:""}],analyze:"",correct:"",score:"",difficult:0},subjectFilter:null,formLoading:!1,rules:{gradeLevel:[{required:!0,message:"请选择年级",trigger:"change"}],subjectId:[{required:!0,message:"请选择学科",trigger:"change"}],title:[{required:!0,message:"请输入题干",trigger:"blur"}],analyze:[{required:!0,message:"请输入解析",trigger:"blur"}],score:[{required:!0,message:"请输入分数",trigger:"blur"}],correct:[{required:!0,message:"请选择正确答案",trigger:"change"}]},richEditor:{dialogVisible:!1,object:null,parameterName:"",instance:null},questionShow:{qType:0,dialog:!1,question:null,loading:!1}}},created:function(){var e=this.$route.query.id,t=this;this.initSubject((function(){t.subjectFilter=t.subjects})),e&&0!==parseInt(e)&&(t.formLoading=!0,c["a"].select(e).then((function(e){t.form=e.response,t.formLoading=!1})))},methods:d({editorReady:function(e){this.richEditor.instance=e;var t=this.richEditor.object[this.richEditor.parameterName];this.richEditor.instance.setContent(t),this.richEditor.instance.focus(!0)},inputClick:function(e,t){this.richEditor.object=e,this.richEditor.parameterName=t,this.richEditor.dialogVisible=!0},editorConfirm:function(){var e=this.richEditor.instance.getContent();this.richEditor.object[this.richEditor.parameterName]=e,this.richEditor.dialogVisible=!1},questionItemRemove:function(e){this.form.items.splice(e,1)},questionItemAdd:function(){var e=this.form.items,t=e[e.length-1],i=String.fromCharCode(t.prefix.charCodeAt()+1);e.push({id:null,prefix:i,content:""})},submitForm:function(){var e=this,t=this;this.$refs.form.validate((function(i){if(!i)return!1;e.formLoading=!0,c["a"].edit(e.form).then((function(i){1===i.code?(t.$message.success(i.message),t.delCurrentView(t).then((function(){t.$router.push("/exam/question/list")}))):(t.$message.error(i.message),e.formLoading=!1)})).catch((function(t){e.formLoading=!1}))}))},resetForm:function(){this.$refs["form"].resetFields()},levelChange:function(){var e=this;this.form.subjectId=null,this.subjectFilter=this.subjects.filter((function(t){return t.level===e.form.gradeLevel}))},showQuestion:function(){this.questionShow.dialog=!0,this.questionShow.qType=this.form.questionType,this.questionShow.question=this.form}},Object(l["b"])("exam",{initSubject:"initSubject"}),{},Object(l["b"])("tagsView",{delCurrentView:"delCurrentView"})),computed:d({},Object(l["c"])("enumItem",["enumFormat"]),{},Object(l["e"])("enumItem",{questionTypeEnum:function(e){return e.exam.question.typeEnum},levelEnum:function(e){return e.user.levelEnum}}),{},Object(l["e"])("exam",{subjects:function(e){return e.subjects}}))},f=m,p=i("2877"),h=Object(p["a"])(f,n,r,!1,null,null,null);t["default"]=h.exports},"294d":function(e,t,i){"use strict";var n=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",{staticStyle:{"line-height":"1.8"}},[1==e.qType?i("div",{directives:[{name:"loading",rawName:"v-loading",value:e.qLoading,expression:"qLoading"}]},[i("div",{staticClass:"q-title",domProps:{innerHTML:e._s(e.question.title)}}),i("div",{staticClass:"q-content"},e._l(e.question.items,(function(t){return i("span",{key:t.id,staticClass:"q-item-contain"},[i("span",{staticClass:"q-item-prefix"},[e._v(e._s(t.prefix))]),i("span",{staticClass:"q-item-content",domProps:{innerHTML:e._s(t.content)}})])})),0)]):2==e.qType?i("div",{directives:[{name:"loading",rawName:"v-loading",value:e.qLoading,expression:"qLoading"}]},[i("div",{staticClass:"q-title",domProps:{innerHTML:e._s(e.question.title)}}),i("div",{staticClass:"q-content"},e._l(e.question.items,(function(t){return i("span",{key:t.id,staticClass:"q-item-contain"},[i("span",{staticClass:"q-item-prefix"},[e._v(e._s(t.prefix))]),i("span",{staticClass:"q-item-content",domProps:{innerHTML:e._s(t.content)}})])})),0)]):3==e.qType?i("div",{directives:[{name:"loading",rawName:"v-loading",value:e.qLoading,expression:"qLoading"}]},[i("div",{staticClass:"q-title",staticStyle:{display:"inline","margin-right":"10px"},domProps:{innerHTML:e._s(e.question.title)}}),i("span",[e._v("（")]),e._l(e.question.items,(function(t){return i("span",{key:t.id},[i("span",{staticClass:"q-item-content",domProps:{innerHTML:e._s(t.content)}})])})),i("span",[e._v("）")])],2):4==e.qType?i("div",{directives:[{name:"loading",rawName:"v-loading",value:e.qLoading,expression:"qLoading"}]},[i("div",{staticClass:"q-title",domProps:{innerHTML:e._s(e.question.title)}})]):5==e.qType?i("div",{directives:[{name:"loading",rawName:"v-loading",value:e.qLoading,expression:"qLoading"}]},[i("div",{staticClass:"q-title",domProps:{innerHTML:e._s(e.question.title)}})]):i("div")])},r=[],o=(i("a9e3"),{name:"QuestionShow",props:{question:{type:Object,default:function(){return{}}},qLoading:{type:Boolean,default:!1},qType:{type:Number,default:0}},methods:{}}),s=o,a=i("2877"),l=Object(a["a"])(s,n,r,!1,null,null,null);t["a"]=l.exports},"2ac8":function(e,t,i){"use strict";var n=i("b775");t["a"]={pageList:function(e){return Object(n["a"])("/api/admin/question/page",e)},edit:function(e){return Object(n["a"])("/api/admin/question/edit",e)},select:function(e){return Object(n["a"])("/api/admin/question/select/"+e)},deleteQuestion:function(e){return Object(n["a"])("/api/admin/question/delete/"+e)}}},"63f4":function(e,t,i){"use strict";var n=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",[i("script",{staticStyle:{height:"300px"},attrs:{id:e.randomId,type:"text/plain"}})])},r=[],o={name:"UE",props:{value:{default:function(){return""}}},data:function(){return{randomId:"editor_"+1e17*Math.random(),instance:null,ready:!1}},watch:{value:function(e,t){null!=e&&this.ready&&(this.instance=UE.getEditor(this.randomId),this.instance.setContent(e))}},mounted:function(){this.initEditor()},beforeDestroy:function(){null!==this.instance&&this.instance.destroy&&this.instance.destroy()},methods:{initEditor:function(){var e=this;this.$nextTick((function(){e.instance=UE.getEditor(e.randomId),e.instance.addListener("ready",(function(){e.ready=!0,e.$emit("ready",e.instance)}))}))},getUEContent:function(){return this.instance.getContent()},setText:function(e){this.instance=UE.getEditor(this.randomId),this.instance.setContent(e)}}},s=o,a=i("2877"),l=Object(a["a"])(s,n,r,!1,null,null,null);t["a"]=l.exports}}]);