#todo 你的建表语句,包含索引
CREATE TABLE `order`  (
                        `order_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单id',
                        `payment_order_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '支付id',
                        `order_type` int(2) NOT NULL COMMENT '订单类型',
                        `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '购买人id',
                        `sku_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'sku',
                        `amount` int(11) NOT NULL COMMENT '购买数量',
                        `sale_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '销售单价',
                        `origin_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '吊牌价',
                        `total_money` decimal(10, 2) NOT NULL COMMENT '购买总金额',
                        `pay_time` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '支付时间',
                        `del_flag` int(2) NOT NULL DEFAULT 0 COMMENT '是否删除（0未删除;1删除）',
                        `del_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '删除时间',
                        `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
                        `create_time` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                        `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
                        `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                        `status` int(2) NULL DEFAULT NULL COMMENT '订单状态 0、待付款(未支付) 1、待发货(已支付) 2、待收货(已发货) 3、已完成(已签收、待评价) 4、已评价 5、已关闭',
                        `received_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '收货时间',
                        `closed_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '订单关闭时间',
                        `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
                        `address` bigint(255) NULL DEFAULT NULL COMMENT '关联地址id',
                        `express_company` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '物流公司',
                        `express_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '物流编号',
                        `express_money` decimal(10, 2) NULL DEFAULT NULL COMMENT '运费',
                        `coupon_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '优惠券编号',
                        `integral_cost` decimal(10, 2) NULL DEFAULT NULL COMMENT '花费积分',
                        `after_sale_status` int(2) NULL DEFAULT NULL COMMENT '售后状态 0待售后 1售后中 2不能售后 3售后成功',
                        UNIQUE INDEX `order_id`(`order_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;




