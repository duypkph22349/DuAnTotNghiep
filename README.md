# DuAnTotNghiep

Trigger for code Voucher
**USE DUANTN
GO
```sql
-- Tạo trigger
CREATE TRIGGER trg_generate_voucher_code
ON dbo.voucher
AFTER INSERT
AS
BEGIN
    DECLARE @InsertedID INT
    SELECT @InsertedID = id FROM inserted
    DECLARE @GeneratedCode NVARCHAR(50)
    DECLARE @Month INT
    DECLARE @Year INT
    DECLARE @RandomChars NVARCHAR(4)
    -- Lấy tháng và năm tạo
    SELECT @Month = MONTH(created_at), @Year = YEAR(created_at) FROM dbo.voucher WHERE id = @InsertedID
    -- Tạo ngẫu nhiên 4 ký tự
    SET @RandomChars = CHAR(65 + CAST(RAND() * 26 AS INT)) +
                       CHAR(65 + CAST(RAND() * 26 AS INT)) +
                       CHAR(65 + CAST(RAND() * 26 AS INT)) +
                       CHAR(65 + CAST(RAND() * 26 AS INT))
    -- Tạo mã voucher dựa trên giá trị types
    SELECT @GeneratedCode = 'T' + CAST(@Month AS NVARCHAR) + 'N' + CAST(@Year AS NVARCHAR) +
        CASE 
            WHEN (SELECT types FROM dbo.voucher WHERE id = @InsertedID) = 1 THEN 'PER' + @RandomChars
            ELSE 'CASH' + @RandomChars
        END
    -- Cập nhật mã voucher vào bản ghi mới được chèn
    UPDATE dbo.voucher
    SET code = @GeneratedCode
    WHERE id = @InsertedID
END**
