// pages/conference/conference.js
var url=require('../../config/config.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    meetList:[],
    search:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.showLoading({
      title:'加载中' ,
    });
    const this_=this;
    wx.request({
      url: url.searchMeetByRecent+'?num=20',
      data: {},
      method: 'GET',
      success: (result)=>{
       if(result.data.statusCode==200){
        this_.setData({
          meetList:result.data.data
        });
        wx.hideLoading();
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
  goCreate(){
    wx.navigateTo({
      url: '../create/create',
    });
  },
  onSearch(e){
    // const this_=this;
    wx.navigateTo({
      url: '../filterMeet/filterMeet?searchText='+this.data.search,
    });
  },
  searchChange(e){
    this.setData({
      search:e.detail
    })
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