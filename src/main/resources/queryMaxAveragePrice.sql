WITH phone_table AS (
    SELECT companyid,
           count(1)   AS count,
           sum(price) AS sum
    FROM phone
    GROUP BY companyid)

SELECT
    c.companyname,
    pt.sum/pt.count AS average_price
FROM phone_table pt
         INNER JOIN company c ON pt.companyid = c.companyid
ORDER BY average_price DESC
LIMIT 1