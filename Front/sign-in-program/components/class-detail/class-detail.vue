<template>
	<view class="class_detail">
		<view class="first-line">
			<text>班级信息</text>
		</view>
		<view class="second-line">
			<view class="class-name">
				<text style="font-weight: bold;">班级名称</text>
				<text>xxx班级</text>
			</view>
			<view class="creater">
				<text style="font-weight: bold;">创建人</text>
				<text>xxx</text>
			</view>
		</view>
		<view class="third-line">
			<view class="class-desc">
				<text style="font-weight: bold;">班级描述</text>
				<text>这是一个非常棒的班级，欢迎大家加入一起学习。</text>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		name:"class-detail",
		data() {
			return {
				
			};
		}
	}
</script>

<style lang="scss">
.class_detail{
	height: 190px;
	width: 90%;
	margin-left: 5%;
	margin-right: 5%;
	border: 1px solid #e3e8ef;
	border-radius: 5px;
	.first-line{
		text{
			font-size: 18px;
			font-weight: bolder;
		}
		padding-left: 10px;
		padding-top: 10px;
	}
	.second-line{
		display: flex;
		justify-content: space-between;
		padding-left: 10px;
		padding-top: 15px;
		padding-right: 10px;
		.class-name{
			display: flex;
			flex-direction: column;
			justify-content: space-between;
			
			height: 40px;
			width: 100px;
		}
		.creater{
			display: flex;
			flex-direction: column;
			
			justify-content: space-between;
			text-align: right;
			height: 40px;
			width: 100px;
		}
	}
	.third-line{
		padding-left: 10px;
		padding-top: 20px;
		padding-right: 10px;
		.class-desc{
			display: flex;
			flex-direction: column;
		}
	}
}
</style>