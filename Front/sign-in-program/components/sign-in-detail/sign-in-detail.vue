<template>
	<view class="body">
		<text> 签到简介(2023-10-22)</text>
		<text style="color: #c8832d;font-weight: bolder;">迟到 1</text>
		<text style="color: #bf482b;font-weight: bolder;">缺勤 3 </text>
	</view>
</template>

<script>
	export default {
		name:"sign-in-detail",
		data() {
			return {
				
			};
		}
	}
</script>

<style>
.body{
	height: 40px;
	display: flex;
	justify-content: space-between;
	align-items: center;
	border: 1px solid #f2f5f9;
	border-radius: 5px;
	background-color: #f2f5f9;
	margin-left:10px;
	margin-right: 10px;
	padding-left: 10px;
	padding-right: 10px;
	margin-bottom: 10px;
}
</style>

