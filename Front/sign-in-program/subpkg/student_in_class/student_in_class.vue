<template>
	<view>
		<view class="top-view">
			<text>班级详情</text>
			<van-button size="small" type="info">修改个人信息</van-button>
		</view>
		<class-detail></class-detail>
		<view class="NotCertified" v-if="!isCertified">
			<input type="text" placeholder=" 我在班级的昵称">
			<input type="number" placeholder=" 学号/工号">
			<van-button type="info">确认信息</van-button>
		</view>
		<view class="Certified" v-else="isCertified">
			<view class="sign-in">
				<input type="number" placeholder=" 请输入签到码">
				<van-button type="info">参与签到</van-button>
			</view>
			<view class="check">
				<van-button  class="checkRecords" @click="checkMyRecords">查看我的考勤记录</van-button>
				<van-button  class="checkNumbers" @click="checkMembers">查看班级成员</van-button>
			</view>
			
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				isCertified:true
			};
		},
		methods:{
			checkMyRecords(){
				uni.navigateTo({
					url:'/subpkg/my-attendance-record/my-attendance-record'
				})
			},
			checkMembers(){
				uni.navigateTo({
					url:'/subpkg/class-members/class-members'
				})
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
.NotCertified{
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
.Certified{
	margin-top: 25px;
	display: flex;
	flex-direction: column;
	align-items: center;
	.sign-in{
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
		display: flex;
		flex-direction: column;
		align-items: center;
		margin-top: 20px;
		button{
			margin-bottom: 10px;
			width: 350px;
			border-radius: 5px;
			background-color: #f2f5f9;
		}
	}
}
</style>
