(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-73ef4f44"],{"0207":function(e,t,o){"use strict";o("ce33")},"87de":function(e,t,o){"use strict";o.r(t);var a=function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("div",{staticClass:"signup-container"},[o("el-form",{ref:"signupForm",staticClass:"signup-form",attrs:{model:e.signupForm,rules:e.rules,"label-position":"left"}},[o("div",{staticClass:"title-container"},[o("h3",{staticClass:"title"},[e._v(" 注册帐号")])]),o("el-form-item",{attrs:{prop:"account",label:"账号："}},[o("el-input",{attrs:{placeholder:"请输入帐号",name:"account",type:"text",tabindex:"1"},model:{value:e.signupForm.account,callback:function(t){e.$set(e.signupForm,"account",t)},expression:"signupForm.account"}})],1),o("el-form-item",{attrs:{prop:"password",label:"密码："}},[o("el-input",{attrs:{placeholder:"请输入密码",name:"password",type:"password",tabindex:"2"},model:{value:e.signupForm.password,callback:function(t){e.$set(e.signupForm,"password",t)},expression:"signupForm.password"}})],1),o("el-form-item",{attrs:{prop:"contact",label:"联系人："}},[o("el-input",{attrs:{placeholder:"请输入联系人",name:"contact",type:"text",tabindex:"3"},model:{value:e.signupForm.contact,callback:function(t){e.$set(e.signupForm,"contact",t)},expression:"signupForm.contact"}})],1),o("el-form-item",{attrs:{prop:"mobile",label:"手机："}},[o("el-input",{attrs:{placeholder:"请输入手机",name:"mobile",type:"text",tabindex:"4"},model:{value:e.signupForm.mobile,callback:function(t){e.$set(e.signupForm,"mobile",t)},expression:"signupForm.mobile"}})],1),o("el-form-item",{attrs:{prop:"email",label:"邮箱："}},[o("el-input",{attrs:{placeholder:"请输入邮箱",name:"email",type:"text",tabindex:"5"},model:{value:e.signupForm.email,callback:function(t){e.$set(e.signupForm,"email",t)},expression:"signupForm.email"}})],1),o("el-form-item",{attrs:{prop:"company",label:"公司名称："}},[o("el-input",{attrs:{placeholder:"请输入公司名称",name:"company",type:"text",tabindex:"6"},model:{value:e.signupForm.company,callback:function(t){e.$set(e.signupForm,"company",t)},expression:"signupForm.company"}})],1),o("el-form-item",{attrs:{prop:"creditCode",label:"信用代码："}},[o("el-input",{attrs:{placeholder:"请输入信用代码",name:"creditCode",type:"text",tabindex:"7"},model:{value:e.signupForm.creditCode,callback:function(t){e.$set(e.signupForm,"creditCode",t)},expression:"signupForm.creditCode"}})],1),o("el-form-item",{attrs:{prop:"customsCode",label:"海关代码："}},[o("el-input",{attrs:{placeholder:"请输入海关代码",name:"customsCode",type:"text",tabindex:"8"},model:{value:e.signupForm.customsCode,callback:function(t){e.$set(e.signupForm,"customsCode",t)},expression:"signupForm.customsCode"}})],1),o("el-form-item",{attrs:{prop:"companyType",label:"公司性质："}},[o("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择公司性质",name:"companyType",type:"text",tabindex:"9"},model:{value:e.signupForm.companyType,callback:function(t){e.$set(e.signupForm,"companyType",t)},expression:"signupForm.companyType"}},[o("el-option",{attrs:{label:"生产企业",value:"produce"}}),o("el-option",{attrs:{label:"外贸企业",value:"trade"}})],1)],1),o("div",{staticStyle:{"padding-left":"120px"}},[o("el-button",{staticStyle:{width:"100%",margin:"0 30px 0 0"},attrs:{loading:e.loading,type:"primary"},on:{click:e.register}},[e._v("注册")])],1)],1)],1)},r=[],s=o("c24f"),n={name:"Signup",data:function(){return{signupForm:{account:"",password:"",contact:"",mobile:"",email:"",company:"",creditCode:"",customsCode:"",companyType:""},rules:{account:[{required:!0,trigger:"change, blur",message:"请输入帐号"}],password:[{required:!0,trigger:"change, blur",message:"请输入密码"}],contact:[{required:!0,trigger:"change, blur",message:"请输入联系人"}],mobile:[{required:!0,trigger:"change, blur",message:"请输入手机"}],email:[{required:!0,trigger:"change, blur",message:"请输入邮箱"}],company:[{required:!0,trigger:"change, blur",message:"请输入公司名称"}],creditCode:[{required:!0,trigger:"change, blur",message:"请输入信用代码"}],customsCode:[{required:!0,trigger:"change, blur",message:"请输入海关代码"}],companyType:[{required:!0,trigger:"change, blur",message:"请选择公司性质"}]},loading:!1}},methods:{register:function(){var e=this;this.$refs.signupForm.validate((function(t){t&&Object(s["f"])(e.signupForm).then((function(t){e.$message({type:"success",duration:3e3,message:"注册成功，请联系管理员授权"}),e.$router.push("/login")}))}))}}},i=n,l=(o("0207"),o("bc4f"),o("dde1"),o("2877")),c=Object(l["a"])(i,a,r,!1,null,"c58e89e2",null);t["default"]=c.exports},bc4f:function(e,t,o){"use strict";o("ef6c")},ce33:function(e,t,o){},dde1:function(e,t,o){"use strict";o("e9b3")},e9b3:function(e,t,o){},ef6c:function(e,t,o){}}]);