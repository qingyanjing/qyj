// pages/create/create.js
var url=require('../../config/config.js');
var appInst =  getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    createtime:'',
    currentDate:new Date().getTime(),
    activeNames:['0'],
    createPerson:wx.getStorageSync('userName'),
    date: '2016-09-01',
    membersList: [],
    membersResult: [],
    selectMembers:[],
    createName:'',
    createContent:'',
    popupShow:false,
    pictureSrc:[],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      const this_=this;
      wx.request({
      url: url.searchUser,
      data: {},
      method: 'POST',
      success: (result)=>{
        if(result.data.statusCode==200){
          this_.setData({
            membersList:result.data.data
          })
        }
      },
      fail: (err)=>{
        console.log(err)
      },
    });
  },
  timeonChange(e){
   const dateTime = this.selectComponent('.dateTime');
   const datastring=dateTime.getValues()[0]+"-"+dateTime.getValues()[1]+"-"+dateTime.getValues()[2]+" "+dateTime.getValues()[3]+":"+dateTime.getValues()[4]
   this.setData({
    createtime:datastring
  })
  },
  onClose() {
    this.setData({ show: false });
  },
  selectTimeMethod(e){
    this.setData({
      popupShow:true
    })
  },
  timeConfirm(){
    this.setData({
      popupShow:false
    })
  },
  collapseChange(event) {
    if(event.target.id=="collapse"){
      if(this.data.activeNames[0]=='1'){
        this.setData({
          activeNames:['0']
        });
       }else{
        this.setData({
          activeNames:['1']
        });
       }
    }
  },
  checkboxChange(event) {
   
    var selectArr=[]
    for(let i=0;i<event.detail.length;i++){
      selectArr.push({id:event.detail[i]})
    };
    this.setData({
      membersResult: event.detail,
      selectMembers:selectArr

    });
  },
  createNameMethod(e){
  this.setData({
    createName:e.detail
  })
  },
  createContentMethod(e){
    this.setData({
      createContent:e.detail
    })
  },
  create(e) {

    wx.showLoading({
      title: '创建中',
    })
    const this_=this;
    wx.request({
     url: url.createMeet,
     data: {
      content:this_.data.createContent,
      header:this_.data.createName,
      organizer:{name:wx.getStorageSync('userName'),id:wx.getStorageSync('userId')},
      date:this_.data.createtime,
      members:this_.data.selectMembers
     },
     header: {'content-type':'application/json'},
     method: 'POST',
     dataType: 'json',
     responseType: 'text',
     success: (result)=>{
       if(result.data.statusCode==200){
        wx.hideLoading()
        appInst.showInfo(result.data.data);
        this_.setData({
          createName:'',
          createContent:'',
          createtime:'',
          membersResult:[],

        })
       }else{
        wx.hideLoading()
        appInst.showInfo(result.data.data);
       }
     },
     fail: ()=>{
      wx.hideLoading()
      appInst.showInfo('接口调用失败');
     },
   });
  },
  toggle(event) {
    const { name } = event.currentTarget.dataset;
    const checkbox = this.selectComponent(`.checkboxes-${name}`);
    checkbox.toggle();
  },
  addPicture(e){
    const this_=this;
    wx.chooseImage({
      count: 3,
      sizeType: ['original','compressed'],
      sourceType: ['album','camera'],
      success: (result)=>{
        this_.setData({
          pictureSrc:result.tempFilePaths
        })
      },
      fail: ()=>{},
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