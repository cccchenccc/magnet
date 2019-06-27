MERGE INTO DMP_MAIN_STOCK STOCK
	USING  (SELECT ISTOCK.STOCK_DATE, ISTOCK.PRODUCT_CODE, MIN(ISTOCK.QUANTITY) M FROM DMP_MAIN_INPUT_STOCK ISTOCK WHERE ISTOCK.SHOWTIME_CODE IS NULL AND ISTOCK.ACTIVE = '1' GROUP BY ISTOCK.STOCK_DATE, ISTOCK.PRODUCT_CODE) NISTOCK
	ON (STOCK.STOCK_DATE = TO_DATE(NISTOCK.STOCK_DATE, 'YYYY-MM-DD') AND STOCK.PRODUCT_CODE = NISTOCK.PRODUCT_CODE)
	WHEN MATCHED THEN
	UPDATE SET STOCK.PROD_STOCK_QUANTITY = NISTOCK.M + STOCK.PROD_STOCK_LOCK_QUANTITY, STOCK.GMT_MODIFIED=SYSDATE