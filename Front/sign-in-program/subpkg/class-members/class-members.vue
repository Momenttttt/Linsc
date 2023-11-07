<template>
	<view>
		<view class="head">
			<text class="Class">XXX班级</text>
		</view>
		<view class="body">
			<student></student>
			<student></student>
			<student></student>
			<student></student>
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
	padding-top:10px;
	.Class{
		font-size: 20px;
		font-weight: bolder;
		padding-top:30px;
		margin-left: 10px;
	}
}
.body{
	margin-top: 15px;
}
</style>
