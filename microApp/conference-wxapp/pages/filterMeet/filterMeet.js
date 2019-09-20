// pages/filterMeet/filterMeet.js
var url=require('../../config/config.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    showFilter:true,
    searchMeetList:[],
    keyword:''

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    const this_=this;
    this_.setData({
      keyword:options.searchText
    })
    wx.request({
      url: url.searchMeet,
      data: {
        header:options.searchText,
      
      },
      header: {'content-type':'application/json'},
      method: 'POST',
      dataType: 'json',
      success: (result)=>{
        if(result.data.statusCode==200){
          this_.setData({
            searchMeetList:result.data.data
          })
        }else{
          this_.setData({
            showFilter:false
          })
         
        }
      },
      fail: ()=>{},
      complete: ()=>{}
    });
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