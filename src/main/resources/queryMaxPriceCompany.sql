WITH max_price  AS (SELECT
                        max(p.price) as price,
                        c.companyid,
                        c.companyname
                    FROM phone p
                             INNER JOIN company c on c.companyid = p.companyid
                    GROUP BY c.companyid,c.companyname)

SELECT
    mp.companyname,
    ph.phohemodel,
    mp.price
FROM max_price mp
         INNER JOIN phone ph On mp.price=ph.price and mp.companyid=ph.companyid