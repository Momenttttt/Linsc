<template>
	<view>
		<view class="top-view">
			<text>班级详情</text>
			<van-button size="small" type="info">修改班级信息</van-button>
		</view>
		<class-detail></class-detail>
		<view class="sign-in">
			<input type="text" placeholder=" 签到简介">
			<input type="number" placeholder=" 请输入签到码">
			<input type="number" placeholder=" 持续时间                               X秒">
			<van-button type="info">发布签到</van-button>
		</view>
		<view class="check">
			<van-button>班级邀请码：55555</van-button>
			<van-button @click="checkRecords">查看班级考勤记录</van-button>
			<van-button @click="checkMembers">查看班级成员</van-button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			};
		},
		methods:{
			checkRecords(){
				uni.navigateTo({
					url: '/subpkg/Attendance-Record/Attendance-Record'
				});
			},
			checkMembers(){
				uni.navigateTo({
					url: '/subpkg/class-members/class-members'
				});
			}
		}
	}
</script>

<style lang="scss">
.top-view{
	height: 60px;
	display: flex;
	justify-content: space-between;
	text{
		padding-left: 10px;
		padding-top: 15px;
		font-size: 20px;
		font-weight: bolder;
	}
	van-button{
		padding-right: 10px;
		padding-top: 15px;
	}
}
.sign-in{
	margin-top: 25px;
	display: flex;
	flex-direction: column;
	align-items: center;
	input{
		margin-bottom: 10px;
		border: 1px solid #a1a1a1;
		border-radius: 5px;
		width: 250px;
		height: 30px;
	}
	button{
		width:150px;
		border-radius: 10px;
	}
}
.check{
	margin-top: 20px;
	display: flex;
	flex-direction: column;
	align-items: center;
	button{
		margin-bottom: 10px;
		width: 350px;
		border-radius: 5px;
		background-color: #f2f5f9;
		height: 35px;
	}
}
</style>
