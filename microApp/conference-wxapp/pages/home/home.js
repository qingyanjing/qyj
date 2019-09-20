// pages/home/home.js
var url=require('../../config/config.js');
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    imgurl:'../../images/icon/images/swiper-img1.jpg',
    meetRecent:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.showLoading({
      title: "加载中"

    });
    const this_=this;
    wx.request({
      url: url.searchMeetByRecent+'?num=3',
      data: {},
      method: 'GET',
      success: (result)=>{
        if(result.data.statusCode==200){
          this_.setData({
            meetRecent:result.data.data
          })
          wx.hideLoading();
        }
      },
      fail: ()=>{},
      complete: ()=>{}
    });

    app.getUserInfo((userInfo) => {
      this.setData({
        userInfo: userInfo
      });
    })
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