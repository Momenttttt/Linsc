<template>
	<view class="box">
		<text class="className">班级名称</text>
		<text class="classDesc">班级描述</text>
		<van-button color=#f2f5f9>进入班级</van-button>
	</view>
</template>

<script>
	export default {
		name:"my-class",
		data() {
			return {
				
			};
		}
	}
</script>

<style lang="scss">
.box{
	width:150px;
	height: 150px;
	border: 1px solid red;
	display: flex;
	flex-direction: column;
	margin-left: 15px;
	margin-right: 15px;
	margin-top: 10px;
	margin-bottom: 10px;
	border:1px solid #e3e8ef;
	border-radius: 10px;
	
	.className{
		font-weight: bolder;
		padding-top: 15px;
		padding-left: 10px;
	}
	
	.classDesc{
		font-size: 15px;
		padding-top: 10px;
		padding-left: 10px;
		padding-bottom: 20px;
	}
	van-button{
		padding-left: 50px;
	}
	button{
		border-radius: 5px;
	}
}
</style>