INSERT INTO Brand VALUES ('BR001',N'Gucci','2023-09-14 10:00:00','2023-09-14 10:30:00',1)
INSERT INTO Brand VALUES ('BR002',N'Louis Vuitton','2023-09-14 10:00:00','2023-09-14 10:30:00',0)
INSERT INTO Brand VALUES ('BR003',N'Herm�s','2023-09-14 10:00:00','2023-09-14 10:30:00',1)
INSERT INTO Brand VALUES ('BR004',N'Burberry','2023-09-14 10:00:00','2023-09-14 10:30:00',0)
INSERT INTO Brand VALUES ('BR005',N'Bvlgari','2023-09-14 10:00:00','2023-09-14 10:30:00',1)

SELECT * FROM Brand
DELETE FROM Brand WHERE Id = 5

USE [DUANTN]
GO
INSERT [dbo].[customer] ([id], [code], [name], [birth_date], [gender], [phone], [address], [city], [country], [status], [created_at], [update_at], [deleted]) VALUES (N'207b5467-d532-409c-aa62-be386bc989a3', N'CUST001                  ', N'John Doe', CAST(N'1990-01-01T00:00:00.000' AS DateTime), 0, N'5555555555', N'123 Main St', N'Anytown', N'CountryX', 1, CAST(N'2023-09-26T12:00:00.000' AS DateTime), CAST(N'2023-09-26T12:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[account] ([id], [email], [password], [status], [id_customer], [created_at], [updated_at], [deleted]) VALUES (N'f616aeeb-1020-456a-8236-ab3b9b8dba68', N'thatv1212@gmail.com', N'$2a$10$/I9zXWN9Im28rTK6qBoW1eu5EMqCTcgPS6Faa.xnt2BozUSMDbPZi', 0, N'207b5467-d532-409c-aa62-be386bc989a3', NULL, NULL, NULL)
GO
SET IDENTITY_INSERT [dbo].[voucher] ON 
GO
INSERT [dbo].[voucher] ( [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount]) VALUES ( N'ABC123                   ', N'Sample Voucher', CAST(N'2023-10-06T10:00:00.000' AS DateTime), CAST(N'2023-10-20T14:30:00.000' AS DateTime), 50, 10.5, 1, CAST(N'2023-10-06T08:00:00.000' AS DateTime), CAST(N'2023-10-06T08:30:00.000' AS DateTime), 0, 0, NULL)
GO
INSERT [dbo].[voucher] ( [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount]) VALUES ( N'Voucher 1                ', N'Voucher 1', CAST(N'2023-10-05T15:53:12.633' AS DateTime), CAST(N'2023-11-04T15:53:12.633' AS DateTime), 1, 8.1120415473449317, 0, CAST(N'2023-10-06T15:53:12.633' AS DateTime), CAST(N'2023-10-06T15:53:12.633' AS DateTime), 0, 0, NULL)
GO
INSERT [dbo].[voucher] ( [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount]) VALUES ( N'Voucher 2                ', N'Voucher 2', CAST(N'2023-10-04T15:53:12.633' AS DateTime), CAST(N'2023-11-03T15:53:12.633' AS DateTime), 58, 48.841095510312435, 1, CAST(N'2023-10-06T15:53:12.633' AS DateTime), CAST(N'2023-10-06T15:53:12.633' AS DateTime), 0, 0, NULL)
GO
INSERT [dbo].[voucher] ( [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount]) VALUES ( N'Voucher 3                ', N'Voucher 3', CAST(N'2023-10-03T15:53:12.633' AS DateTime), CAST(N'2023-11-02T15:53:12.633' AS DateTime), 67, 31.027456496898747, 0, CAST(N'2023-10-06T15:53:12.633' AS DateTime), CAST(N'2023-10-06T15:53:12.633' AS DateTime), 1, 0, NULL)
GO
INSERT [dbo].[voucher] ( [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount]) VALUES ( N'Voucher 4                ', N'Voucher 4', CAST(N'2023-10-02T15:53:12.637' AS DateTime), CAST(N'2023-11-01T15:53:12.637' AS DateTime), 93, 3.6055725584942704, 1, CAST(N'2023-10-06T15:53:12.637' AS DateTime), CAST(N'2023-10-06T15:53:12.637' AS DateTime), 0, 1, NULL)
GO
INSERT [dbo].[voucher] ( [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount]) VALUES ( N'Voucher 5                ', N'Voucher 5', CAST(N'2023-10-01T15:53:12.637' AS DateTime), CAST(N'2023-10-31T15:53:12.637' AS DateTime), 21, 8.0769658660361454, 0, CAST(N'2023-10-06T15:53:12.637' AS DateTime), CAST(N'2023-10-06T15:53:12.637' AS DateTime), 0, 0, NULL)
GO
INSERT [dbo].[voucher] ( [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount]) VALUES ( N'Voucher 6                ', N'Voucher 6', CAST(N'2023-09-30T15:53:12.637' AS DateTime), CAST(N'2023-10-30T15:53:12.637' AS DateTime), 21, 23.798123720553196, 1, CAST(N'2023-10-06T15:53:12.637' AS DateTime), CAST(N'2023-10-06T15:53:12.637' AS DateTime), 1, 0, NULL)
GO
INSERT [dbo].[voucher] ( [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount]) VALUES ( N'Voucher 7                ', N'Voucher 7', CAST(N'2023-09-29T15:53:12.637' AS DateTime), CAST(N'2023-10-29T15:53:12.637' AS DateTime), 12, 7.2298180187032157, 0, CAST(N'2023-10-06T15:53:12.637' AS DateTime), CAST(N'2023-10-06T15:53:12.637' AS DateTime), 0, 0, NULL)
GO
INSERT [dbo].[voucher] ( [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount]) VALUES ( N'Voucher 8                ', N'Voucher 8', CAST(N'2023-09-28T15:53:12.637' AS DateTime), CAST(N'2023-10-28T15:53:12.637' AS DateTime), 45, 21.563865785576272, 1, CAST(N'2023-10-06T15:53:12.637' AS DateTime), CAST(N'2023-10-06T15:53:12.637' AS DateTime), 0, 1, NULL)
GO
INSERT [dbo].[voucher] ( [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount]) VALUES ( N'Voucher 9                ', N'Voucher 9', CAST(N'2023-09-27T15:53:12.637' AS DateTime), CAST(N'2023-10-27T15:53:12.637' AS DateTime), 79, 1.511227403979865, 0, CAST(N'2023-10-06T15:53:12.637' AS DateTime), CAST(N'2023-10-06T15:53:12.637' AS DateTime), 1, 0, NULL)
GO
INSERT [dbo].[voucher] ( [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount]) VALUES ( N'Voucher 10               ', N'Voucher 10', CAST(N'2023-09-26T15:53:12.637' AS DateTime), CAST(N'2023-10-26T15:53:12.637' AS DateTime), 33, 4.3247837587917957, 1, CAST(N'2023-10-06T15:53:12.637' AS DateTime), CAST(N'2023-10-06T15:53:12.637' AS DateTime), 0, 0, NULL)
GO
INSERT [dbo].[voucher] ( [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount]) VALUES ( NULL, N'VOUCHERXX3', CAST(N'2023-10-18T19:25:00.000' AS DateTime), CAST(N'2023-10-26T19:25:00.000' AS DateTime), 112, 13, 1, NULL, NULL, NULL, NULL, NULL)
GO
INSERT [dbo].[voucher] ( [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount]) VALUES ( NULL, N'VOUCHERXX3', CAST(N'2023-10-30T10:15:00.000' AS DateTime), CAST(N'2023-11-24T10:15:00.000' AS DateTime), 1213, 1232, 1, NULL, NULL, NULL, NULL, NULL)
GO
INSERT [dbo].[voucher] ( [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount]) VALUES ( NULL, N'vouchex', CAST(N'2023-11-11T08:44:00.000' AS DateTime), CAST(N'2023-10-07T08:44:00.000' AS DateTime), 12, 12, 1, NULL, NULL, NULL, NULL, NULL)
GO
INSERT [dbo].[voucher] ( [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount]) VALUES ( NULL, N'efwef', CAST(N'2023-10-07T13:47:00.000' AS DateTime), CAST(N'2023-10-07T08:52:00.000' AS DateTime), 12, 12, 1, NULL, NULL, NULL, NULL, NULL)
GO
INSERT [dbo].[voucher] ( [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount]) VALUES ( NULL, N'efweffefae', CAST(N'2023-10-07T13:47:00.000' AS DateTime), CAST(N'2023-10-07T08:52:00.000' AS DateTime), 12, 12, 1, NULL, NULL, NULL, NULL, NULL)
GO
INSERT [dbo].[voucher] ( [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount]) VALUES ( NULL, N'VOUCHERXX3', CAST(N'2023-10-07T09:50:00.000' AS DateTime), CAST(N'2023-10-07T09:50:00.000' AS DateTime), 1213, 1232, 1, NULL, NULL, NULL, NULL, NULL)
GO
INSERT [dbo].[voucher] ( [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount]) VALUES ( NULL, N'efweffeaef', CAST(N'2023-10-07T09:05:00.000' AS DateTime), CAST(N'2023-10-07T09:05:00.000' AS DateTime), 12, 14, 1, NULL, NULL, NULL, NULL, NULL)
GO
INSERT [dbo].[voucher] ( [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount]) VALUES ( N'sQakry                   ', N'efwef', CAST(N'2023-10-07T22:25:00.000' AS DateTime), CAST(N'2023-10-07T22:25:00.000' AS DateTime), 12, 17, 0, NULL, NULL, NULL, NULL, NULL)
GO
INSERT [dbo].[voucher] ( [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount]) VALUES ( NULL, N'efwef', NULL, NULL, 14, 14, 1, NULL, NULL, NULL, NULL, NULL)
GO
