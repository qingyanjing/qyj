// 服务器域名
// 创建会议
const createMeet = 'http://127.0.0.1:18011/createMeet';
// 删除会议
const deleteMeet = 'http://127.0.0.1:18011/deleteMeet';
// 修改会议记录
const updateMeet = 'http://127.0.0.1:18011/updateMeet';
// 搜索会议记录
const searchMeet = 'http://127.0.0.1:18011/searchMeet';
// 获取用户列表
const searchUser = 'http://127.0.0.1:18011/searchUser';
// 获取近期会议
const searchMeetByRecent='http://127.0.0.1:18011/searchMeetByRecent';
// 根据id查询会议
const getMeetById = 'http://127.0.0.1:18011/getMeetById';
//登陆
const wxLogin ='http://127.0.0.1:18011/wxLogin';
//注册
const register ='http://127.0.0.1:18011/registerUser';
const searchMeetByUser = 'http://127.0.0.1:18011/searchMeetByUser'
// 设置收藏
const setFavorites = 'http://127.0.0.1:18011/setFavorites'
// 搜索收藏
const searchFavorites = 'http://127.0.0.1:18011/searchFavorites'
// 取消收藏
const cancelFavorites = 'http://127.0.0.1:18011/cancelFavorites'
// 添加笔记
const addNote = 'http://127.0.0.1:18011/addNote'


module.exports = {
	createMeet: createMeet,
	deleteMeet: deleteMeet,
	updateMeet: updateMeet,
	searchMeet: searchMeet,
	searchUser:searchUser,
	searchMeetByRecent:searchMeetByRecent,
	getMeetById:getMeetById,
	wxLogin: wxLogin,
	register: register,
	searchMeetByUser:searchMeetByUser,
	setFavorites:setFavorites,
	searchFavorites:searchFavorites,
	cancelFavorites:cancelFavorites,
	addNote:addNote

};
