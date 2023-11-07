<template>
	<view class="body">
		<van-image src="https://img.yzcdn.cn/vant/cat.jpeg" 
			  round
			  width="2rem"
			  height="2rem">
		</van-image>
		<text>XXX学生</text>
	</view>
</template>

<script>
	export default {
		name:"student",
		data() {
			return {
				
			};
		}
	}
</script>

<style lang="scss">
.body{
	height: 40px;
	display: flex;
	align-items: center;
	border: 1px solid #f2f5f9;
	border-radius: 5px;
	background-color: #f2f5f9;
	margin-left:10px;
	margin-right: 10px;
	padding-left: 10px;
	padding-right: 10px;
	margin-bottom: 10px;
	van-image{
		padding-top: 5px;
	}
	text{
		padding-left: 10px;
	}
}
</style>