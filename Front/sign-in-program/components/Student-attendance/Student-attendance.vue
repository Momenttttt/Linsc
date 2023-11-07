<template>
	<view class="body">
		<text>XXX学生(202100XXXXXX)</text>
		<text style="color: #bf482b;">缺勤</text>
	</view>
</template>

<script>
	export default {
		name:"Student-attendance",
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