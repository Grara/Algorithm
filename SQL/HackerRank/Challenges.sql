--각 학생이 만든 문제수를 출력하는데, 만든 문제수가 같은 학생들은 전체에서 최대로 만든 문제수가 아닐 경우 제외
--WITH를 안쓰면 쓰는게 상당히 귀찮아지는 문제

WITH Counter AS (
    SELECT hacker_id, count(1) as cnt
    FROM Challenges
    GROUP BY hacker_id
)

SELECT H.hacker_id, name, cnt
FROM Hackers H
JOIN Counter C ON H.hacker_id = C.hacker_id
WHERE C.cnt = (SELECT MAX(cnt) FROM Counter)
OR    (SELECT COUNT(1) FROM Counter WHERE cnt = C.cnt) = 1
ORDER BY cnt DESC, H.hacker_id