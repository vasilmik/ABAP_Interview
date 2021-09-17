WITH phone_table AS (
    SELECT companyid,
           count(1)   AS count,
           sum(price) AS sum
    FROM phone
    GROUP BY companyid)

--SELECT * FROM phone_table;

SELECT c.companyname,
       coalesce(p.sum,0) AS sum,
       coalesce(p.count,0) AS count
FROM company c
         LEFT JOIN phone_table p
                   on c.companyid = p.companyid