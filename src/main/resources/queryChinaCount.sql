WITH phone_table AS (
    SELECT companyid,
           count(1)   AS count,
           sum(price) AS sum
    FROM phone
    GROUP BY companyid)

SELECT
    c.companycountry,
    c.companyname,
    pt.count AS count
FROM phone_table pt
         INNER JOIN company c ON pt.companyid = c.companyid
WHERE companycountry = 'China'
;