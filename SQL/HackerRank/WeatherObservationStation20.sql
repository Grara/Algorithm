-- 문제 : 중간값을 구해라
SELECT CAST(ROUND(AVG(LAT_N), 4) AS NUMERIC(10,4)) AS MEDIAN --중간값 평균을 구하고 소수점 5번째에서 반올림하고 나머지는 짜름
FROM   
       (SELECT LAT_N
             , ROW_NUMBER() OVER(ORDER BY LAT_N) AS ROW_NUM
             , COUNT(*) OVER() AS CNT
        FROM   STATION) AS SUB

WHERE  CASE --전체 행수에 따라 분기처리
            WHEN CNT % 2 = 1 AND ROW_NUM = (CNT + 1) / 2 --전체 행수가 홀수일 경우 가운데 하나만
            THEN 1
            WHEN CNT % 2 = 0 AND (ROW_NUM = (CNT / 2) OR ROW_NUM = (CNT / 2) + 1) --전체 행수가 짝수일 경우 (전체행수 / 2)와 {(전체행수/2) + 1} 인 행을 구해야함
            THEN 1
       END = 1