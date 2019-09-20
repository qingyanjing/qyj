// pages/myMeet/myMeet.js
var url=require('../../config/config.js');
var app =  getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    myMeetList:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.showLoading({title: '加载中'});
    const this_=this;
    if(options.meetType=="collect"){
      wx.setNavigationBarTitle({
        title: '我收藏的'
      });

      wx.request({
        url: url.searchFavorites,
        data: {userId:wx.getStorageSync('userId')},
        header: {'Accept':'application/json'},
        method: 'POST',
        dataType: 'json',
        success: (result)=>{
          if(result.data.statusCode=='200'){
            this_.setData({
              myMeetList:result.data.data
            })
          }
          else{
            this_.setData({
              myMeetList:false
            })
            app.showInfo('没有记录')
          }
          wx.hideLoading();
        },
        
        fail: ()=>{
          app.showInfo('接口请求失败')
          wx.hideLoading();
        },
      });
    }else{
      wx.setNavigationBarTitle({
        title: '我参与的'
      });
      wx.request({
        url: url.searchMeetByUser,
        data: {id:wx.getStorageSync('userId')},
        header: {'Accept':'application/json'},
        method: 'POST',
        dataType: 'json',
        success: (result)=>{
          if(result.data.statusCode=='200'){
            this_.setData({
              myMeetList:result.data.data
            })
          }
          else{
            app.showInfo('没有记录')
          }
          wx.hideLoading();
        },
        fail: ()=>{
          wx.hideLoading();
          app.showInfo('接口请求失败')
        },
      });
    }
  },
  meetRecentMethod(e){
    wx.navigateTo({
      url: '../detail/detail?detailId='+e.currentTarget.dataset.id
  });
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})