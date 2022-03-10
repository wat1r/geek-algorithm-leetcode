



```javascript
/*可任意修改样式，或恢复预设值，保存后生效
这是个综合的示例，所以对很多地方都进行了修改
相对“默认样式”而作的修改会用  红色 标注*/


.output_wrapper/*此属性为全局*/
{
     font-size: 15px;/*更改默认的字体大小*/
     color: #3e3e3e;
     line-height: 1.8; /*更改默认的行距*/
     word-spacing:2px; /*更改默认的词间距*/
     letter-spacing:2px;/*更改默认的字符间距*/
     font-family: "Helvetica Neue",Helvetica,"Hiragino Sans GB","Microsoft YaHei",Arial,sans-serif;   

     /*增加背景网格*/
     background-image: linear-gradient(90deg, rgba(50, 0, 0, 0.05) 3%, rgba(0, 0, 0, 0) 3%), linear-gradient(360deg,rgba(50, 0, 0, 0.05) 3%, rgba(0, 0, 0, 0) 3%);
     background-size: 20px 20px;
     background-position: center center;
}
.output_wrapper *
{
  font-size: inherit  ;
  color: inherit;
  line-height: inherit;
  margin:0px;
  padding:0px;
}

p {/*段落*/
  margin: 1.7em 0px;/*更改默认的段落距离*/
}
h1,h2,h3,h4,h5,h6 {
  margin: 1.6em 0px;/*增加默认的标题间距*/
  font-weight:bold;
}
h1 {
  font-size: 1.6em  ;
 
}
h2 {
  font-size: 1.4em;
}
h3 {
  font-size: 1.3em;
}
h4 {
  font-size: 1.2em;
}
h5 {
  font-size: 1em;
}
h6 {
  font-size: 1em;
}

h2{/*增加对h3标题的修改*/
   border-bottom:2px  solid   rgb(239,112,96);
   font-size: 1.6em;
}

h3{/*增加对h3标题的修改*/
   border-bottom:2px  solid   rgb(239,112,96);
   font-size: 1.3em;
}
h3 span{/*增加对h3标题字体的修改*/
  display:inline-block;
  font-weight:normal;
  background:rgb(239,112,96);
  color:#ffffff;
  padding:3px 10px 1px;
  border-top-right-radius: 3px; 
  border-top-left-radius: 3px; 
  margin-right:3px;
}
h3:after{/*增加对h3标题后面的修饰*/
  display: inline-block; 
  content:" "; 
  vertical-align: bottom;
  border-bottom: 36px solid #EFEBE9;
  border-right: 20px solid transparent;  
}

h4{/*增加对h4标题的修改*/
   border-bottom:2px  solid   rgb(239,112,96);
   font-size: 1.2em;
}
h4 span{/*增加对h3标题字体的修改*/
  display:inline-block;
  font-weight:normal;
  background:rgb(239,112,96);
  color:#ffffff;
  padding:3px 10px 1px;
  border-top-right-radius: 3px; 
  border-top-left-radius: 3px; 
  margin-right:3px;
}

h4:after{/*增加对h3标题后面的修饰*/
  display: inline-block; 
  content:" "; 
  vertical-align: bottom;
  border-bottom: 36px solid #EFEBE9;
  border-right: 20px solid transparent;  
}



ul, ol {
  padding-left: 32px;
}
ul{ /*无序列表*/
    list-style-type: disc;
}
ol { /*有序列表*/
  list-style-type: decimal;
}
li *  
{
 /* color: #3e3e3e;*/
} 

li{  /*在公众号下，改变不了li符号的属性（如颜色），并会影响其子元素的属性;而在其它博客平台中，则能正常使用*/ 
    margin-bottom: 0.5em;
/*  color:#159957; */    
}
.code_size_default  /*代码块默认size*/
{
  line-height: 18px;
  font-size: 14px; 
  font-weight:normal;
  word-spacing:0px; 
  letter-spacing:0px; 
}
.code_size_tight /*代码块紧凑size*/
{
   line-height: 15px; 
   font-size: 11px; 
   font-weight:normal;
   word-spacing:-3px; 
   letter-spacing:0px; 
}
pre code /*代码块*/
{           
     font-family: Consolas, Inconsolata, Courier, monospace;
     border-radius: 0px;
}
blockquote { /*引用块*/
  display: block;
  padding: 15px 1rem;
  font-size: 0.9em;
  padding-right: 15px;
  margin: 1em 0;
  
  /*更改默认值*/
  color: #000000;
  border-left: 5px solid rgb(239,112,96);
  background: #EFEBE9;
  
  overflow: auto;
  overflow-scrolling: touch; 
  word-wrap: normal;
  word-break: normal;  
}
blockquote p {
    margin: 0px;
}

a { /*超链接*/
  text-decoration: none;
  color: #1e6bb8;
  word-wrap:break-word;
}

strong  /*强调*/
{
  font-weight: bold;
  color:#e96900;/*更改默认的颜色*/
}
em /*斜体*/
{
 /*font-style:italic;*//*把默认的斜体去掉*/
 color:#6200EA;/*更改默认的颜色*/
}
del /*删除线*/
{
 font-style:italic;
 text-decoration:none;/*把默认的删除线去掉*/
 color:#2962FF;/*更改默认的颜色*/
}
strong em/*强调的斜体*/
{
font-weight: bold;
color:#C51162;/*更改默认的颜色*/
}


hr {  /*分隔线*/
  height: 1px;
  margin: 1.5rem 0px;
  border: none;
  border-top: 1px dashed #A5A5A5;
}


code /*行内代码*/
{
    word-wrap: break-word;
    padding: 2px 4px;
    border-radius: 4px;
    margin:0 2px;
    color:#f82375;/*更改默认的颜色*/
    background:#f8f8f8;
}
img
{
  display: block;
  margin:0 auto;  /*图片水平居中*/
  /* margin:0 0;  */ /*图片水平居左，如需要请打开*/
  max-width:100%;
}
figcaption/*图片描述文字*/
{
  margin-top:10px;
  text-align:center;
   /* text-align:left;  */ /*当图片水平居左时，请打开*/
  color:#999;
  font-size: 0.7em;
}

/*================表格开始================*/
table
{
 display:table;
 width: 100% ;
 text-align: left;
}
tbody {
  border: 0;
}

table tr {
  border: 0;
  border-top: 1px solid #CCC;
  background-color: white;
 
}

/*隔行改变行的背景色*/
table tr:nth-child(2n) {
  background-color: #F8F8F8;
}


table tr th, table tr td {
  font-size: 1em;
  border: 1px solid #CCC;
  padding: 0.5em 1em;
  text-align: left;
}
/*表头的属性*/
table tr th {
 font-weight: bold;
  background-color: #F0F0F0;
}
/*================表格结束================*/



/*================数学公式开始================*/
.katex-display {/*块公式*/
  font-size:1.22em; 
}
.katex
{/*行内公式*/
  padding:8px 3px;
}
.katex-display > .katex
{/*块公式*/
   display:inline-block;
   text-align:center;
   padding:3px;
}
.katex img
{/*行内公式对应的图片*/
  display:inline-block;
  vertical-align:middle;
}
/*================数学公式结束================*/

a[href^="#"] sup
{/*注脚*/
  vertical-align:super;
  margin:0 2px;  
  padding:1px 3px; 
  color: #ffffff;
  background:#666666;
  font-size:0.7em;
}

/*================任务列表开始================*/
.task-list-list {
  list-style-type: none;
}
.task-list-list.checked {/*已完成*/
  color: #3e3e3e;
}

.task-list-list.uncheck {/*未完成*/
  color: #bfc1bf;
}
.task-list-list .icon_uncheck, .task-list-list .icon_check {
  display: inline-block;
  vertical-align: middle;
  margin-right: 10px;
}
.task-list-list .icon_check:before
{/*已完成*/
    content: "√";
    border: 2px solid #3e3e3e;
    color:red;
}
.task-list-list .icon_uncheck:before
{/*未完成*/
   content: "x";
   border: 2px solid #bfc1bf;
    color: #bfc1bf;
}
.task-list-list .icon_check:before, .task-list-list .icon_uncheck:before
{/*标志框*/
  padding:2px;
  padding-left: 5px;
  padding-right: 8px;
  border-radius:5px;
}
/*================任务列表结束================*/

.toc
{/*总目录*/
  margin-left:25px;
}
.toc_item
{/*每条目录*/
  display:block;

}
.toc_left
{/*每级目录的缩进*/
  margin-left:25px;
}
```

