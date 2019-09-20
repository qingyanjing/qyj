//app.js
const api=require('./config/config.js')
App({
  data: {
    userInfo: {}
  },
  onLaunch: function () {
    // 登录
    let that=this;
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
        
        wx.setStorageSync("LogCode", res.code);
        console.log("code:"+res.code)
        wx.request({
          url: api.wxLogin,
          data:{code:res.code},
          method:'GET',
          header:{
            'content-type': 'application/x-www-form-urlencoded',
            'Accept': 'application/json'
          },
          success:function(res){
            console.log("---server-" + res)
            if (res.data.statusCode=='200'){
              wx.setStorageSync("openId", res.data.data.openId);
              wx.setStorageSync("userId", res.data.data.userId);
              wx.setStorageSync("userName", res.data.data.userName);
              wx.setStorageSync("avatarUrl", res.data.data.avatarUrl);
              if (that.userInfoReadyCallback) {
                that.userInfoReadyCallback(res.data.data.avatarUrl)
              }
              console.log(JSON.stringify(res))
            }else{
              console.log("请求出错："+res.data.data)
            }
          },
          error: function (err) {
            console.log(err);
          }
        })
      }
    })
   
  },
  checkLoginStatus:function(){
    let this_=this;
    let loginFlag=wx.getStorageInfoSync('loginFlag');
    if(loginFlag){
      wx.checkSession({
        success: (result)=>{
          let userStorageInfo = wx.getStorageSync('userInfo');
          if(userStorageInfo){
            this_.globalData.userInfo=JSON.parse(userStorageInfo);
          }
          else{
            this_.showInfo('缓存信息缺失');
            console.error('登录成功后将用户信息存在Storage的userStorageInfo字段中，该字段丢失');
          }
        },
        // session_Key过期 重新请求登录
        fail: ()=>{
          this_.doLogin()
        },
      });
    }
    // 无登录标识 请求登录
    else{
      this_.doLogin()
    }
  },
  // 登录
  doLogin:function(){
    let this_=this;
    console.log("是谁在调用啊");
    wx.login({
      success: (lofinRes)=>{
      if(lofinRes.code){
        wx.getUserInfo({
          withCredentials: 'true',
          lang: 'zh_CN',
          success: (infoRes)=>{
            // 调用接口获取后台数据
            wx.request({
              url: '',
              data: {},
              // header: {'content-type':'application/json'},
              // method: 'GET',
              // dataType: 'json',
              // responseType: 'text',
              success: (res)=>{
                if(res.code==200){
                  this_.globalData.userInfo=res.userInfo;
                  wx.setStorageSync('userInfo',JSON.stringify(res.userInfo));
                  wx.setStorageSync('loginFlag',res.skey);
                  callback();
                }
                else{
                  this_.showInfo(res.error)
                }
              },
              fail: ()=>{
                // this_.showInfo('请求接口失败')
              },
            });
          },
          // 获取userInfo失败，提示用户开启权限开启权限
          fail: ()=>{
            this_.showInfo('请允许获得您的头像昵称')
          },
        });
      }
      },
      fail: (error)=>{
         // 调用 wx.login 接口失败
        //  that.showInfo('接口调用失败');
         console.log(error);
      },
    });
  },
  getUserInfo (cb) {
    console.log("是谁在调用");
    if (this.globalData.userInfo) {
      typeof cb == "function" && cb(this.globalData.userInfo)
    } else {
      //调用登陆接口
      // wx.login({
      //   success: () => {
      //     wx.getUserInfo({
      //       withCredentials: false,
      //       success:(res) => {
      //         // console.log(res)
      //         this.globalData.userInfo = res.userInfo
      //         typeof cb == "function" && cb(this.globalData.userInfo)
      //       }
      //     })
      //   }
      // })
    }
  },
   // 封装 wx.showToast 方法
   showInfo: function (info = 'error', icon = 'none') {
    wx.showToast({
        title: info,
        icon: icon,
        duration: 1500,
        mask: true
    });
},
  globalData: {
    userInfo: null
  }
})