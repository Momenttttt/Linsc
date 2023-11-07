<template>
	<view>
		<view class="head">
			<text class="Class">XXX班级</text>
		</view>
		<view class="body">
			<sign-in-detail @click.native="checkDetail"></sign-in-detail>
			<sign-in-detail @click.native="checkDetail"></sign-in-detail>
			<sign-in-detail @click.native="checkDetail"></sign-in-detail>
			<sign-in-detail @click.native="checkDetail"></sign-in-detail>
		</view>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			};
		},
		methods:{
			checkDetail(){
				uni.navigateTo({
					url:'/subpkg/sign-in-more-detail/sign-in-more-detail'
				})
			}
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
