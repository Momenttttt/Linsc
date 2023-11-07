<template>
	<view>
		<view class="head">
			<van-image
			  round
			  width="6rem"
			  height="6rem"
			  src="https://img.yzcdn.cn/vant/cat.jpeg"
			  class="head"
			  />
			<text>姓名</text>
		</view>
		<view class="body">
			<view class="user_id">
				<text>用户ID</text>
				<text>123456</text>
			</view>
			<view class="gender">
				<text>性别</text>
				<text>男</text>
			</view>
			<view class="user_desc">
				<text>个人简介</text>
				<text>xxxxxxxxx</text>
			</view>
			<button>注销账号</button>
			<button>退出登录</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			};
		}
	}
</script>

<style lang="scss">
.head{
	width: 100%;
	height: 150px;
	display: flex;
	flex-direction: column;
	justify-content: space-around;
	align-items: center;
	van-image{
		margin-top: 50px;
	}
	text{
		margin-top: 5px;
		font-size: 20px;
		font-weight: bolder;
	}
}
.body{
	display: flex;
	justify-content: space-around;
	flex-direction:column;
	.user_id{
		margin-top: 30px;
		margin-left: 10%;
		margin-right: 10%;
		margin-bottom: 20px;
		width: 80%;
		height: 40px;
		display: flex;
		justify-content: space-between;
		border: 1px solid #d0ddf6;
		border-radius: 5px;
		background-color: #f2f5f9;
		text{
			margin-right: 10px;
			margin-top: 10px;
			margin-left: 10px;
		}
	}
	.gender{
		margin-left: 10%;
		margin-right: 10%;
		margin-bottom: 20px;
		width: 80%;
		height: 40px;
		display: flex;
		justify-content: space-between;
		border: 1px solid #d0ddf6;
		border-radius: 5px;
		background-color: #f2f5f9;
		text{
			margin-right: 10px;
			margin-top: 10px;
			margin-left: 10px;
		}
	}
	.user_desc{
		margin-left: 10%;
		margin-right: 10%;
		margin-bottom: 20px;
		width: 80%;
		height: 40px;
		display: flex;
		justify-content: space-between;
		border: 1px solid #d0ddf6;
		border-radius: 5px;
		background-color: #f2f5f9;
		text{
			margin-right: 10px;
			margin-top: 10px;
			margin-left: 10px;
		}
	}
	button{
		width: 80%;
		height: 40px;
		border: 1px solid #d0ddf6;
		border-radius: 5px;
		background-color: #f2f5f9;
		margin-bottom: 10px;
		margin-top: 10px;
	}
}
</style>
