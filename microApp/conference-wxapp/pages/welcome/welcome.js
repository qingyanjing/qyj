const app = getApp();
const api = require('../../config/config.js')
Page({
  data: {
    showDialog: false,
    inputName:'',
    remind: '加载中',
    angle: 0,
    userInfo: {},
    detail:{}
  },
  goToIndex() {
    wx.showLoading({
      title: '加载中',
    })
    wx.switchTab({
      url: '../home/home',
    });
  },
  onLoad () {
    wx.setNavigationBarTitle({
      title: `上海电科会议记录`
    })
    //函数穿越
    app.userInfoReadyCallback = res => {
      this.setData({
        userInfo: {
          avatarUrl: res
        }   
      })
    }
  },
  onReady () {
    setTimeout( () => {
      this.setData({
        remind: ''
      });
    }, 1000);
    wx.onAccelerometerChange( (res) => {
      let angle = -(res.x * 30).toFixed(1);
      if (angle > 14) { angle = 14; }
      else if (angle < -14) { angle = -14; }
      if (this.data.angle !== angle) {
        this.setData({
          angle: angle
        });
      }
    });
  },
  // 获取输入框的名字
  usernameValue(e) {
    this.setData({
      inputName: e.detail
    })
  },
  onDialogClose(e) {
    // 点击确定后的操作
    if (e.detail == "confirm") {
      let inputName=this.data.inputName
      let that=this
      if(inputName.length>1){
        console.log(JSON.stringify(this.data.detail))
        wx.request({
          url: api.register,
          data: {
            encryptedData: this.data.detail.encryptedData,
            iv: this.data.detail.iv,
            openId:wx.getStorageSync('openId'),
            name:inputName
          },
          method: 'POST',
          header: {
            'Accept': 'application/json'
          },
          success: function (res) {
            if (res.data.statusCode == '200') {
              console.log(JSON.stringify(res));
              wx.setStorageSync("userId", res.data.data.id);
              wx.setStorageSync("userName", res.data.data.name);
              that.setData({
                showDialog: false
              });
              that.goToIndex();
            } else {
              console.log("请求出错：" + res.data.data)
            }
          },
          error: function (err) {
            console.log(err);
          }
        })
        
      }else{      
        this.setData({
          showDialog: false
        });
        this.setData({
          showDialog: true
        });
        app.showInfo("至少两个字符")
      }
    } else {
      this.setData({
        showDialog: false
      })
    }

  },
  getUserInfo: function (e) {
    console.log(e)
    console.log(wx.getStorageSync('openId'))
    let userId=wx.getStorageSync("userId")
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      detail: e.detail
    })
    if(userId==null||userId==undefined){
      this.setData({
        showDialog: true
      })
      
    }else{
      this.goToIndex();
    }   
    
  }
});