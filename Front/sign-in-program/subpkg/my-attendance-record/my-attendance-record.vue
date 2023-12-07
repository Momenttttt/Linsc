<template>
	<view>
		<view>
			<view class="head">
				<text class="Class">签到简介</text>
			</view>
			<view class="body">
				<my-sign-in-detail></my-sign-in-detail>
				<my-sign-in-detail></my-sign-in-detail>
				<my-sign-in-detail></my-sign-in-detail>
				<my-sign-in-detail></my-sign-in-detail>
			</view>
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
	margin-top:15px;
}

</style>
