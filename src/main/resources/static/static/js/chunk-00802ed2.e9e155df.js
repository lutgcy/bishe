(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-00802ed2"],{4127:function(e,t,r){"use strict";var a=r("d233"),n=r("b313"),o={brackets:function(e){return e+"[]"},indices:function(e,t){return e+"["+t+"]"},repeat:function(e){return e}},i=Date.prototype.toISOString,l={delimiter:"&",encode:!0,encoder:a.encode,encodeValuesOnly:!1,serializeDate:function(e){return i.call(e)},skipNulls:!1,strictNullHandling:!1},c=function e(t,r,n,o,i,c,s,u,p,d,f,y){var m=t;if("function"===typeof s)m=s(r,m);else if(m instanceof Date)m=d(m);else if(null===m){if(o)return c&&!y?c(r,l.encoder):r;m=""}if("string"===typeof m||"number"===typeof m||"boolean"===typeof m||a.isBuffer(m)){if(c){var g=y?r:c(r,l.encoder);return[f(g)+"="+f(c(m,l.encoder))]}return[f(r)+"="+f(String(m))]}var h,b=[];if("undefined"===typeof m)return b;if(Array.isArray(s))h=s;else{var v=Object.keys(m);h=u?v.sort(u):v}for(var w=0;w<h.length;++w){var O=h[w];i&&null===m[O]||(b=Array.isArray(m)?b.concat(e(m[O],n(r,O),n,o,i,c,s,u,p,d,f,y)):b.concat(e(m[O],r+(p?"."+O:"["+O+"]"),n,o,i,c,s,u,p,d,f,y)))}return b};e.exports=function(e,t){var r=e,i=t?a.assign({},t):{};if(null!==i.encoder&&void 0!==i.encoder&&"function"!==typeof i.encoder)throw new TypeError("Encoder has to be a function.");var s="undefined"===typeof i.delimiter?l.delimiter:i.delimiter,u="boolean"===typeof i.strictNullHandling?i.strictNullHandling:l.strictNullHandling,p="boolean"===typeof i.skipNulls?i.skipNulls:l.skipNulls,d="boolean"===typeof i.encode?i.encode:l.encode,f="function"===typeof i.encoder?i.encoder:l.encoder,y="function"===typeof i.sort?i.sort:null,m="undefined"!==typeof i.allowDots&&i.allowDots,g="function"===typeof i.serializeDate?i.serializeDate:l.serializeDate,h="boolean"===typeof i.encodeValuesOnly?i.encodeValuesOnly:l.encodeValuesOnly;if("undefined"===typeof i.format)i.format=n["default"];else if(!Object.prototype.hasOwnProperty.call(n.formatters,i.format))throw new TypeError("Unknown format option provided.");var b,v,w=n.formatters[i.format];"function"===typeof i.filter?(v=i.filter,r=v("",r)):Array.isArray(i.filter)&&(v=i.filter,b=v);var O,j=[];if("object"!==typeof r||null===r)return"";O=i.arrayFormat in o?i.arrayFormat:"indices"in i?i.indices?"indices":"repeat":"indices";var k=o[O];b||(b=Object.keys(r)),y&&b.sort(y);for(var _=0;_<b.length;++_){var D=b[_];p&&null===r[D]||(j=j.concat(c(r[D],D,k,u,p,d?f:null,v,y,m,g,w,h)))}var x=j.join(s),S=!0===i.addQueryPrefix?"?":"";return x.length>0?S+x:""}},4328:function(e,t,r){"use strict";var a=r("4127"),n=r("9e6a"),o=r("b313");e.exports={formats:o,parse:n,stringify:a}},"75d9":function(e,t,r){"use strict";r.r(t);var a=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"app-container"},[r("el-row",[r("el-form",{ref:"searchForm",attrs:{inline:!0,model:e.searchFromData}},[r("el-form-item",{attrs:{prop:"applyState",label:"申请状态",size:"medium"}},[r("el-select",{staticStyle:{margin:"0 10px"},attrs:{clearable:"",placeholder:"请选择",size:"small"},on:{change:e.selectChange},model:{value:e.searchFromData.applyState,callback:function(t){e.$set(e.searchFromData,"applyState",t)},expression:"searchFromData.applyState"}},e._l(e.applyStateOptions,(function(e){return r("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),1)],1),r("el-form-item",{attrs:{prop:"dateRange",label:"申请日期",size:"medium"}},[r("el-date-picker",{attrs:{size:"small",clearable:"",type:"monthrange",align:"right","unlink-panels":"","range-separator":"至","start-placeholder":"开始月份","end-placeholder":"结束月份","picker-options":e.pickerOptions},model:{value:e.searchFromData.dateRange,callback:function(t){e.$set(e.searchFromData,"dateRange",t)},expression:"searchFromData.dateRange"}})],1),r("el-form-item",[r("el-button",{attrs:{type:"primary",icon:"el-icon-search",size:"mini"},on:{click:e.search}},[e._v("查找")]),r("el-button",{attrs:{icon:"el-icon-refresh",size:"mini"},on:{click:function(t){return e.resetForm("searchForm")}}},[e._v("重置")])],1)],1)],1),r("el-row",{staticStyle:{"margin-left":"70%"}},[r("router-link",{attrs:{to:"/employee/additional/newapply/index"}},[r("el-link",{staticStyle:{margin:"0 10px",color:"#1890ff"},attrs:{icon:"el-icon-edit"}},[e._v("申请")])],1),r("el-button",{attrs:{plain:"",type:"warning",icon:"el-icon-document",size:"mini"}},[e._v("导出当前页")]),r("el-button",{attrs:{plain:"",disabled:!1,type:"danger",icon:"el-icon-document",size:"mini"}},[e._v("导出所有页")])],1),r("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],staticStyle:{width:"90%",margin:"10px 30px"},attrs:{"element-loading-text":"拼命加载中","element-loading-spinner":"el-icon-loading","element-loading-background":"rgba(0, 0, 0, 0.7)",data:e.tableData,"header-cell-style":{background:"#EBEEF5",color:"#606266",textAlign:"center"},"cell-style":{textAlign:"center"}}},[r("el-table-column",{attrs:{label:"申请人"},scopedSlots:e._u([{key:"default",fn:function(t){return[r("el-popover",{attrs:{trigger:"hover",placement:"top"}},[r("p",[e._v("姓名: "+e._s(t.row.emp_name))]),r("p",[e._v("住址: "+e._s(t.row.emp_address))]),r("div",{staticClass:"name-wrapper",attrs:{slot:"reference"},slot:"reference"},[r("el-tag",{attrs:{size:"medium",type:""}},[e._v(e._s(t.row.emp_name))])],1)])]}}])}),r("el-table-column",{attrs:{label:"申请项"},scopedSlots:e._u([{key:"default",fn:function(t){return[r("el-tag",{attrs:{type:"success"}},[e._v(e._s(e._f("applyType")(t.row.apply_type)))])]}}])}),r("el-table-column",{attrs:{label:"日期"},scopedSlots:e._u([{key:"default",fn:function(t){return[r("i",{staticClass:"el-icon-time"}),r("span",{staticStyle:{"margin-left":"10px"}},[e._v(e._s(e._f("dateFilter")(t.row.apply_date)))])]}}])}),r("el-table-column",{attrs:{label:"申请额度",width:"100"},scopedSlots:e._u([{key:"default",fn:function(t){return[r("svg-icon",{attrs:{"icon-class":"money"}}),r("span",{staticStyle:{"margin-left":"10px"}},[e._v(e._s(t.row.apply_money))])]}}])}),r("el-table-column",{attrs:{label:"申请文件"},scopedSlots:e._u([{key:"default",fn:function(t){return[r("el-button",{attrs:{type:"text",size:"mini"},on:{click:function(r){return e.handleDownload(t.row)}}},[e._v("下载")])]}}])}),r("el-table-column",{attrs:{label:"申请状态"},scopedSlots:e._u([{key:"default",fn:function(t){return[r("el-tag",{key:e.items[t.row.apply_state].label,attrs:{type:e.items[t.row.apply_state].type,effect:"dark"}},[e._v(" "+e._s(e.items[t.row.apply_state].label)+" ")])]}}])}),r("el-table-column",{attrs:{label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[r("el-button",{attrs:{disabled:0!==t.row.apply_state,size:"mini",type:"success",plain:""},on:{click:function(r){return e.handleAlter(t.row)}}},[e._v("修改")])]}}])})],1),[r("el-pagination",{staticStyle:{"margin-top":"20px"},attrs:{background:"",layout:"total, sizes, prev, pager, next, jumper",total:e.listQuery.total,"current-page":e.listQuery.pageNum,"page-size":e.listQuery.pageSize,"page-sizes":[5,10,20,30,50]},on:{"update:currentPage":function(t){return e.$set(e.listQuery,"pageNum",t)},"update:current-page":function(t){return e.$set(e.listQuery,"pageNum",t)},"update:pageSize":function(t){return e.$set(e.listQuery,"pageSize",t)},"update:page-size":function(t){return e.$set(e.listQuery,"pageSize",t)},"current-change":e.pageChange,"size-change":e.sizeChange}})]],2)},n=[],o=(r("841c"),r("ac1f"),r("2b3d"),r("d3b7"),r("3ca3"),r("ddb0"),r("1276"),r("d491")),i=r("4328"),l=r.n(i),c=r("c1df"),s=r.n(c),u={filters:{applyType:function(e){var t={child_edu:"子女教育",big_sick:"大病医疗",continue_edu:"继续教育",old_man:"赡养老人",credit:"住房贷款利息",rent:"租房租金"};return t[e]?t[e]:"显示错误："+e},dateFilter:function(e){return e?s()(e).format("YYYY-MM"):e}},data:function(){return{applyStateOptions:[{value:"0",label:"待审核"},{value:"1",label:"已批准"},{value:"2",label:"已驳回"}],pickerOptions:{shortcuts:[{text:"本月",onClick:function(e){e.$emit("pick",[new Date,new Date])}},{text:"今年至今",onClick:function(e){var t=new Date,r=new Date((new Date).getFullYear(),0);e.$emit("pick",[r,t])}},{text:"最近六个月",onClick:function(e){var t=new Date,r=new Date;r.setMonth(r.getMonth()-6),e.$emit("pick",[r,t])}}]},listQuery:{total:0,pageNum:1,pageSize:10},searchFromData:{applyState:void 0,dateRange:[]},loading:!1,searchChange:!1,tableData:[],items:[{type:"info",label:"待审核"},{type:"success",label:"已批准"},{type:"danger",label:"已驳回"}]}},watch:{searchFromData:{handler:function(){this.searchChange=!0},deep:!0}},created:function(){this.getAddApplyList(this.listQuery.pageNum,this.listQuery.pageSize)},methods:{setZero:function(){},selectChange:function(){this.search()},handleAlter:function(e){this.$router.push({path:"/employee/additional/alterapply/index",name:"AlterApply",query:{row:JSON.stringify(e)}})},handleDelete:function(e){var t=this;this.$confirm("此操作将永久删除, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){Object(o["b"])(l.a.stringify({id:e.id})).then((function(){t.$message.info("操作成功"),t.search()})).catch((function(){t.$message.error("操作失败")}))})).catch((function(){t.$message({type:"info",message:"已取消删除"})}))},handleDownload:function(e){Object(o["c"])({fileName:e.apply_file}).then((function(t){var r=new Blob([t.data],{type:"multipart/form-data"}),a=document.createElement("a"),n=window.URL.createObjectURL(r);a.href=n,a.download=e.apply_file.split("\\").pop(),document.body.appendChild(a),a.click(),document.body.removeChild(a),window.URL.revokeObjectURL(n)}))},search:function(){var e=this,t={};this.searchFromData.applyState&&(t["applyState"]=this.searchFromData.applyState),2===this.searchFromData.dateRange.length&&(t["startDate"]=this.searchFromData.dateRange[0],t["endDate"]=this.searchFromData.dateRange[1]),this.loading=!0,this.searchChange&&(this.listQuery.pageNum=1,this.searchChange=!1),Object(o["e"])(t,this.listQuery.pageNum,this.listQuery.pageSize,this.$store.getters.token).then((function(t){e.tableData=t.data.list,e.listQuery.total=t.data.total,e.loading=!1})).catch((function(){e.loading=!1}))},getAddApplyList:function(e,t){this.search(e,t)},pageChange:function(){this.getAddApplyList(this.listQuery.pageNum,this.listQuery.pageSize)},sizeChange:function(e){this.getAddApplyList(1,e)},resetForm:function(e){this.$refs[e].resetFields()}}},p=u,d=r("2877"),f=Object(d["a"])(p,a,n,!1,null,"5169679a",null);t["default"]=f.exports},"9e6a":function(e,t,r){"use strict";var a=r("d233"),n=Object.prototype.hasOwnProperty,o={allowDots:!1,allowPrototypes:!1,arrayLimit:20,decoder:a.decode,delimiter:"&",depth:5,parameterLimit:1e3,plainObjects:!1,strictNullHandling:!1},i=function(e,t){for(var r={},a=t.ignoreQueryPrefix?e.replace(/^\?/,""):e,i=t.parameterLimit===1/0?void 0:t.parameterLimit,l=a.split(t.delimiter,i),c=0;c<l.length;++c){var s,u,p=l[c],d=p.indexOf("]="),f=-1===d?p.indexOf("="):d+1;-1===f?(s=t.decoder(p,o.decoder),u=t.strictNullHandling?null:""):(s=t.decoder(p.slice(0,f),o.decoder),u=t.decoder(p.slice(f+1),o.decoder)),n.call(r,s)?r[s]=[].concat(r[s]).concat(u):r[s]=u}return r},l=function(e,t,r){for(var a=t,n=e.length-1;n>=0;--n){var o,i=e[n];if("[]"===i)o=[],o=o.concat(a);else{o=r.plainObjects?Object.create(null):{};var l="["===i.charAt(0)&&"]"===i.charAt(i.length-1)?i.slice(1,-1):i,c=parseInt(l,10);!isNaN(c)&&i!==l&&String(c)===l&&c>=0&&r.parseArrays&&c<=r.arrayLimit?(o=[],o[c]=a):o[l]=a}a=o}return a},c=function(e,t,r){if(e){var a=r.allowDots?e.replace(/\.([^.[]+)/g,"[$1]"):e,o=/(\[[^[\]]*])/,i=/(\[[^[\]]*])/g,c=o.exec(a),s=c?a.slice(0,c.index):a,u=[];if(s){if(!r.plainObjects&&n.call(Object.prototype,s)&&!r.allowPrototypes)return;u.push(s)}var p=0;while(null!==(c=i.exec(a))&&p<r.depth){if(p+=1,!r.plainObjects&&n.call(Object.prototype,c[1].slice(1,-1))&&!r.allowPrototypes)return;u.push(c[1])}return c&&u.push("["+a.slice(c.index)+"]"),l(u,t,r)}};e.exports=function(e,t){var r=t?a.assign({},t):{};if(null!==r.decoder&&void 0!==r.decoder&&"function"!==typeof r.decoder)throw new TypeError("Decoder has to be a function.");if(r.ignoreQueryPrefix=!0===r.ignoreQueryPrefix,r.delimiter="string"===typeof r.delimiter||a.isRegExp(r.delimiter)?r.delimiter:o.delimiter,r.depth="number"===typeof r.depth?r.depth:o.depth,r.arrayLimit="number"===typeof r.arrayLimit?r.arrayLimit:o.arrayLimit,r.parseArrays=!1!==r.parseArrays,r.decoder="function"===typeof r.decoder?r.decoder:o.decoder,r.allowDots="boolean"===typeof r.allowDots?r.allowDots:o.allowDots,r.plainObjects="boolean"===typeof r.plainObjects?r.plainObjects:o.plainObjects,r.allowPrototypes="boolean"===typeof r.allowPrototypes?r.allowPrototypes:o.allowPrototypes,r.parameterLimit="number"===typeof r.parameterLimit?r.parameterLimit:o.parameterLimit,r.strictNullHandling="boolean"===typeof r.strictNullHandling?r.strictNullHandling:o.strictNullHandling,""===e||null===e||"undefined"===typeof e)return r.plainObjects?Object.create(null):{};for(var n="string"===typeof e?i(e,r):e,l=r.plainObjects?Object.create(null):{},s=Object.keys(n),u=0;u<s.length;++u){var p=s[u],d=c(p,n[p],r);l=a.merge(l,d,r)}return a.compact(l)}},b313:function(e,t,r){"use strict";var a=String.prototype.replace,n=/%20/g;e.exports={default:"RFC3986",formatters:{RFC1738:function(e){return a.call(e,n,"+")},RFC3986:function(e){return e}},RFC1738:"RFC1738",RFC3986:"RFC3986"}},d233:function(e,t,r){"use strict";var a=Object.prototype.hasOwnProperty,n=function(){for(var e=[],t=0;t<256;++t)e.push("%"+((t<16?"0":"")+t.toString(16)).toUpperCase());return e}(),o=function(e){var t;while(e.length){var r=e.pop();if(t=r.obj[r.prop],Array.isArray(t)){for(var a=[],n=0;n<t.length;++n)"undefined"!==typeof t[n]&&a.push(t[n]);r.obj[r.prop]=a}}return t},i=function(e,t){for(var r=t&&t.plainObjects?Object.create(null):{},a=0;a<e.length;++a)"undefined"!==typeof e[a]&&(r[a]=e[a]);return r},l=function e(t,r,n){if(!r)return t;if("object"!==typeof r){if(Array.isArray(t))t.push(r);else{if("object"!==typeof t)return[t,r];(n.plainObjects||n.allowPrototypes||!a.call(Object.prototype,r))&&(t[r]=!0)}return t}if("object"!==typeof t)return[t].concat(r);var o=t;return Array.isArray(t)&&!Array.isArray(r)&&(o=i(t,n)),Array.isArray(t)&&Array.isArray(r)?(r.forEach((function(r,o){a.call(t,o)?t[o]&&"object"===typeof t[o]?t[o]=e(t[o],r,n):t.push(r):t[o]=r})),t):Object.keys(r).reduce((function(t,o){var i=r[o];return a.call(t,o)?t[o]=e(t[o],i,n):t[o]=i,t}),o)},c=function(e,t){return Object.keys(t).reduce((function(e,r){return e[r]=t[r],e}),e)},s=function(e){try{return decodeURIComponent(e.replace(/\+/g," "))}catch(t){return e}},u=function(e){if(0===e.length)return e;for(var t="string"===typeof e?e:String(e),r="",a=0;a<t.length;++a){var o=t.charCodeAt(a);45===o||46===o||95===o||126===o||o>=48&&o<=57||o>=65&&o<=90||o>=97&&o<=122?r+=t.charAt(a):o<128?r+=n[o]:o<2048?r+=n[192|o>>6]+n[128|63&o]:o<55296||o>=57344?r+=n[224|o>>12]+n[128|o>>6&63]+n[128|63&o]:(a+=1,o=65536+((1023&o)<<10|1023&t.charCodeAt(a)),r+=n[240|o>>18]+n[128|o>>12&63]+n[128|o>>6&63]+n[128|63&o])}return r},p=function(e){for(var t=[{obj:{o:e},prop:"o"}],r=[],a=0;a<t.length;++a)for(var n=t[a],i=n.obj[n.prop],l=Object.keys(i),c=0;c<l.length;++c){var s=l[c],u=i[s];"object"===typeof u&&null!==u&&-1===r.indexOf(u)&&(t.push({obj:i,prop:s}),r.push(u))}return o(t)},d=function(e){return"[object RegExp]"===Object.prototype.toString.call(e)},f=function(e){return null!==e&&"undefined"!==typeof e&&!!(e.constructor&&e.constructor.isBuffer&&e.constructor.isBuffer(e))};e.exports={arrayToObject:i,assign:c,compact:p,decode:s,encode:u,isBuffer:f,isRegExp:d,merge:l}},d491:function(e,t,r){"use strict";r.d(t,"e",(function(){return n})),r.d(t,"c",(function(){return o})),r.d(t,"a",(function(){return i})),r.d(t,"d",(function(){return l})),r.d(t,"b",(function(){return c}));var a=r("b775");function n(e,t,r,n){return Object(a["a"])({url:"/api/additionapply/search",method:"get",params:{condition:e,pageNum:t,pageSize:r,token:n}})}function o(e){return Object(a["a"])({url:"/api/addition/download",method:"post",data:e,responseType:"blob"})}function i(e){return Object(a["a"])({url:"/api/addition/agree",method:"post",data:e})}function l(e){return Object(a["a"])({url:"/api/addition/reject",method:"post",data:e})}function c(e){return Object(a["a"])({url:"/api/addition/delete",method:"post",data:e})}}}]);