// pages/detail/detail.js
var url=require('../../config/config.js');
var appInst =  getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    collectState:false,
    message:'',
    noteState:false,
    detailMeetData:{},
    nodeValue:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.showLoading({
      title: '加载中',
    });
    const this_=this;
    console.log(url.getMeetById+'?meetId='+options.detailId+'&userId='+wx.getStorageSync('userId'))
    wx.request({
      url: url.getMeetById+'?meetId='+options.detailId+'&userId='+wx.getStorageSync('userId'),
      data: {},
      header: {'content-type':'application/json'},
      method: 'GET',
      dataType: 'json',
      success: (result)=>{
        if(result.data.statusCode==200){
          wx.setNavigationBarTitle({
            title: result.data.data.header
          })
          this_.setData({
            detailMeetData:result.data.data,
            collectState:result.data.data.favorites
          })
          wx.hideLoading();
        }
        else{
          console.log(result)
        }
      },
      fail: (err)=>{
        console.log(err)
      },
      complete: ()=>{}
    });
  },
  nodeContent(e){
    this.setData({
      nodeValue:e.detail
    })
  },
  collectMethod(){
    const this_=this;
    if(!this.data.collectState){
      wx.request({
        url: url.setFavorites,
        data: {userId:wx.getStorageSync('userId'),meetId:this_.data.detailMeetData.id},
        header: {'content-type':'application/json'},
        method: 'POST',
        dataType: 'json',
        responseType: 'text',
        success: (result)=>{
          if(result.data.statusCode=='200'){
            appInst.showInfo('收藏成功');
            this_.setData({
              collectState:true
            });
          }
          else{
            appInst.showInfo('收藏失败');
          }
        },
        fail: (err)=>{
          console.log(err)
        },
      });
    }else{
      wx.request({
        url: url.cancelFavorites,
        data: {userId:wx.getStorageSync('userId'),meetId:this_.data.detailMeetData.id},
        header: {'content-type':'application/json'},
        method: 'POST',
        dataType: 'json',
        responseType: 'text',
        success: (result)=>{
          if(result.data.statusCode=='200'){
            appInst.showInfo('取消成功');
            this_.setData({
              collectState:false
            });
          }
          else{
            appInst.showInfo('取消收藏失败');
          }
        },
        fail: (err)=>{
          console.log(err)
        },
      });
    }
   
  },
  noteMethod(){
    this.setData({
      noteState:!this.data.noteState
    })
  },
  saveNoteMethod(){
    const this_=this;
    wx.request({
      url: url.addNote,
      data: {userId:wx.getStorageSync('userId'),meetId:this_.data.detailMeetData.id,noteContent:this_.data.nodeValue},
      header: {'content-type':'application/json'},
      method: 'POST',
      dataType: 'json',
      responseType: 'text',
      success: (result)=>{
        if(result.data.statusCode=='200'){
          appInst.showInfo('保存成功');
        }
      },
      fail: (err)=>{
        appInst.showInfo('请求失败');
      },
     
    });
  
    this.setData({
      noteState:!this.data.noteState
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