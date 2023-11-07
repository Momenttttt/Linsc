<template>
	<view>
		<view class="top">
			<text>加入新班级</text>
		</view>
		<view class="body">
			<input type="number" v-model="inviteCode" placeholder="  请输入邀请码">
			<button>加入班级</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
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
	input{
		height: 40px;
		width: 200px;
		border: 1px solid #a1a1a1;
		border-radius: 5px;
		margin-top: 150px;
		margin-left: 85px;
		margin-bottom: 30px;
		
	}
	button{
		background-color:#4e80ee;
		color: #ffffff;
		border-radius: 10px;
		width: 40%;
	}
}
</style>
