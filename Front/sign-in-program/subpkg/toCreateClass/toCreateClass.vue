<template>
	<view>
		<view class="top">
			<text>创建新班级</text>
		</view>
		<view class="body">
			<input class="firstInput" type="text" v-model="createrName" placeholder=" 创建人名称">
			<input type="text" v-model="className" placeholder=" 班级名称">
			<input type="text" v-model="classDesc" placeholder=" 班级简介">
			<input type="number" v-model="inviteCode" placeholder=" 邀请码:555555">
			<button>创建班级</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				inviteCode:"",
				createrName:"",
				className:"",
				classDesc:"",
				inviteCode:""
			};
		}
	}
</script>

<style lang="scss">
.top{
	height: 40px;
	display: flex;
	justify-content: space-between;
	border-bottom:1px solid  rgba(229, 231, 235, 1);
	text{
		padding-left: 10px;
		padding-top: 10px;
		font-size: 18px;
		font-weight: bolder;
	}
}
.body{
	display: flex;
	flex-direction: column;
	.firstInput{
		margin-top: 100px;
	}
	input{
		height: 40px;
		width: 200px;
		border: 1px solid #a1a1a1;
		border-radius: 5px;

		margin-left: 85px;
		margin-bottom: 10px;
	}
	button{
		background-color:#4e80ee;
		color: #ffffff;
		border-radius: 10px;
		width: 40%;
		margin-top: 30px;
	}
}
</style>
