USE [DUANTN]
GO
SET IDENTITY_INSERT [dbo].[roles] ON 
GO
INSERT [dbo].[roles] ([id], [name], [status], [created_at], [update_at], [deleted], [role]) VALUES (1, N'User1', 0, NULL, NULL, 1, N'User1')
GO
INSERT [dbo].[roles] ([id], [name], [status], [created_at], [update_at], [deleted], [role]) VALUES (2, N'User2', 1, NULL, NULL, 1, N'User2')
GO
INSERT [dbo].[roles] ([id], [name], [status], [created_at], [update_at], [deleted], [role]) VALUES (3, N'User3', 0, NULL, NULL, 1, N'User3')
GO
INSERT [dbo].[roles] ([id], [name], [status], [created_at], [update_at], [deleted], [role]) VALUES (4, N'User4', 1, NULL, NULL, 1, N'User4')
GO
INSERT [dbo].[roles] ([id], [name], [status], [created_at], [update_at], [deleted], [role]) VALUES (5, N'User5', 0, NULL, NULL, 1, N'User5')
GO
INSERT [dbo].[roles] ([id], [name], [status], [created_at], [update_at], [deleted], [role]) VALUES (6, N'User6', 0, NULL, NULL, 1, N'User6')
GO
INSERT [dbo].[roles] ([id], [name], [status], [created_at], [update_at], [deleted], [role]) VALUES (7, N'User7', 0, NULL, NULL, 1, N'User7')
GO
INSERT [dbo].[roles] ([id], [name], [status], [created_at], [update_at], [deleted], [role]) VALUES (8, N'User8', 0, NULL, NULL, 1, N'User8')
GO
SET IDENTITY_INSERT [dbo].[roles] OFF
GO
INSERT [dbo].[employee] ([id], [id_roles], [code], [name], [gender], [birth_date], [thanh_pho], [thanh_pho_name], [huyen], [huyen_name], [xa], [xa_name], [address], [phone], [email], [status], [created_at], [update_at], [deleted], [cccd], [image], [password]) VALUES (N'32978b98-6923-4af1-b5dc-00ceb04db8f6', 1, N'NV01', N'Phạm Mỹ Tho', 0, CAST(N'2003-06-17T00:00:00.000' AS DateTime), NULL, NULL, NULL, NULL, NULL, NULL, N'Hà Nội', N'0961745487', N'my@gmail.com             ', 0, NULL, NULL, 0, N'023133245213', N'face2.jpg', NULL)
GO
INSERT [dbo].[employee] ([id], [id_roles], [code], [name], [gender], [birth_date], [thanh_pho], [thanh_pho_name], [huyen], [huyen_name], [xa], [xa_name], [address], [phone], [email], [status], [created_at], [update_at], [deleted], [cccd], [image], [password]) VALUES (N'915854e3-65af-451c-b466-0904b615f3be', 2, N'NV01', N'Phạm Khương Duy', 0, CAST(N'2002-05-11T00:00:00.000' AS DateTime), NULL, NULL, NULL, NULL, NULL, NULL, N'Thái Bình', N'0141234144', N'duy@gmail.com            ', 0, NULL, NULL, 0, N'031241362432', N'face1.jpg', NULL)
GO
INSERT [dbo].[employee] ([id], [id_roles], [code], [name], [gender], [birth_date], [thanh_pho], [thanh_pho_name], [huyen], [huyen_name], [xa], [xa_name], [address], [phone], [email], [status], [created_at], [update_at], [deleted], [cccd], [image], [password]) VALUES (N'183f9459-ee9e-45cb-982c-1564882f6fd4', 3, N'NV01', N'Nguyễn Văn Long', 1, CAST(N'2003-11-20T00:00:00.000' AS DateTime), NULL, NULL, NULL, NULL, NULL, NULL, N'Nam Định', N'0348452345', N'long@gmail.com           ', 1, NULL, NULL, 1, N'043423642342', N'face3.jpg', NULL)
GO
INSERT [dbo].[employee] ([id], [id_roles], [code], [name], [gender], [birth_date], [thanh_pho], [thanh_pho_name], [huyen], [huyen_name], [xa], [xa_name], [address], [phone], [email], [status], [created_at], [update_at], [deleted], [cccd], [image], [password]) VALUES (N'a06ab4b4-e457-4733-9037-383e59502921', 4, N'NV01', N'Trần Thị Huyền', 0, CAST(N'2003-02-07T00:00:00.000' AS DateTime), NULL, NULL, NULL, NULL, NULL, NULL, N'Thanh Hóa', N'0823623564', N'huyen@gmail.com          ', 1, NULL, NULL, 1, N'034394573243', N'face4.jpg', NULL)
GO
INSERT [dbo].[employee] ([id], [id_roles], [code], [name], [gender], [birth_date], [thanh_pho], [thanh_pho_name], [huyen], [huyen_name], [xa], [xa_name], [address], [phone], [email], [status], [created_at], [update_at], [deleted], [cccd], [image], [password]) VALUES (N'6b07adc1-adfc-4588-806c-44563581ca72', 5, N'NV01', N'Phạm Anh Dũng', 1, CAST(N'2003-07-22T00:00:00.000' AS DateTime), NULL, NULL, NULL, NULL, NULL, NULL, N'Hồ Chí Minh', N'0312744342', N'dung@gmail.com           ', 0, NULL, NULL, 1, N'034384264624', N'face5.jpg', NULL)
GO
INSERT [dbo].[employee] ([id], [id_roles], [code], [name], [gender], [birth_date], [thanh_pho], [thanh_pho_name], [huyen], [huyen_name], [xa], [xa_name], [address], [phone], [email], [status], [created_at], [update_at], [deleted], [cccd], [image], [password]) VALUES (N'6fe4567c-a06f-425e-b6f4-4980cf025c01', 6, N'NV01', N'Phạm Tuyết Nga', 0, CAST(N'2000-01-25T00:00:00.000' AS DateTime), NULL, NULL, NULL, NULL, NULL, NULL, N'Nam Định', N'0923472342', N'nga@gmail.com            ', 0, NULL, NULL, 1, N'038473265354', N'face6.jpg', NULL)
GO
INSERT [dbo].[employee] ([id], [id_roles], [code], [name], [gender], [birth_date], [thanh_pho], [thanh_pho_name], [huyen], [huyen_name], [xa], [xa_name], [address], [phone], [email], [status], [created_at], [update_at], [deleted], [cccd], [image], [password]) VALUES (N'40d4f149-f6b7-4377-add9-4b27b17f4919', 7, N'NV01', N'Vũ Thị Phương', 1, CAST(N'1999-01-02T00:00:00.000' AS DateTime), NULL, NULL, NULL, NULL, NULL, NULL, N'Phú Thọ', N'0742364321', N'phuong@gmail.com         ', 1, NULL, NULL, 1, N'034857475234', N'face7.jpg', NULL)
GO
INSERT [dbo].[employee] ([id], [id_roles], [code], [name], [gender], [birth_date], [thanh_pho], [thanh_pho_name], [huyen], [huyen_name], [xa], [xa_name], [address], [phone], [email], [status], [created_at], [update_at], [deleted], [cccd], [image], [password]) VALUES (N'680f30a4-13cf-4ccd-a308-539977ea3866', 8, N'NV01', N'Trần Khánh Thịnh', 1, CAST(N'1998-02-11T00:00:00.000' AS DateTime), NULL, NULL, NULL, NULL, NULL, NULL, N'Hưng Yên', N'0834372423', N'thinh@gmail.com          ', 0, NULL, NULL, 1, N'034835674567', N'face8.jpg', NULL)
GO
INSERT [dbo].[employee] ([id], [id_roles], [code], [name], [gender], [birth_date], [thanh_pho], [thanh_pho_name], [huyen], [huyen_name], [xa], [xa_name], [address], [phone], [email], [status], [created_at], [update_at], [deleted], [cccd], [image], [password]) VALUES (N'4308d505-c2ce-4162-8e14-57af8f50fe7d', 1, N'NV01', N'Quản Thu Hà', 0, CAST(N'2003-05-01T00:00:00.000' AS DateTime), NULL, NULL, NULL, NULL, NULL, NULL, N'Bình Định', N'0538532223', N'ha@gmail.com             ', 1, NULL, NULL, 1, N'034854735624', N'face9.jpg', NULL)
GO
INSERT [dbo].[employee] ([id], [id_roles], [code], [name], [gender], [birth_date], [thanh_pho], [thanh_pho_name], [huyen], [huyen_name], [xa], [xa_name], [address], [phone], [email], [status], [created_at], [update_at], [deleted], [cccd], [image], [password]) VALUES (N'35355a54-22a8-45d0-be09-69d775ecf993', 2, N'NV01', N'Vũ Văn Ngọ', 1, CAST(N'2003-01-01T00:00:00.000' AS DateTime), NULL, NULL, NULL, NULL, NULL, NULL, N'Huế', N'0834623462', N'ngoc@gmail.com           ', 0, NULL, NULL, 1, N'034875353453', N'face10.jpg', NULL)
GO
INSERT [dbo].[employee] ([id], [id_roles], [code], [name], [gender], [birth_date], [thanh_pho], [thanh_pho_name], [huyen], [huyen_name], [xa], [xa_name], [address], [phone], [email], [status], [created_at], [update_at], [deleted], [cccd], [image], [password]) VALUES (N'417b2f81-cca4-4a17-b491-7ba7e04e889a', 1, N'NV01', N'Phạm Thị Ánh', 0, CAST(N'2004-06-22T00:00:00.000' AS DateTime), NULL, NULL, NULL, NULL, NULL, NULL, N'Duy Nhất, Vũ Thư, Thái Bình', N'0423434212', N'27@gmail.com             ', 0, NULL, NULL, 0, N'23123123123', N'face27.jpg', NULL)
GO
INSERT [dbo].[employee] ([id], [id_roles], [code], [name], [gender], [birth_date], [thanh_pho], [thanh_pho_name], [huyen], [huyen_name], [xa], [xa_name], [address], [phone], [email], [status], [created_at], [update_at], [deleted], [cccd], [image], [password]) VALUES (N'ac2542ce-c4a4-4b43-bb82-83b1865abcde', 3, N'NV01', N'Trương Thị Trúc', 1, CAST(N'2000-01-08T00:00:00.000' AS DateTime), NULL, NULL, NULL, NULL, NULL, NULL, N'Quảng Ninh', N'0438264242', N'truc@gmail.com           ', 0, NULL, NULL, 1, N'034823572652', N'face11.jpg', NULL)
GO
INSERT [dbo].[employee] ([id], [id_roles], [code], [name], [gender], [birth_date], [thanh_pho], [thanh_pho_name], [huyen], [huyen_name], [xa], [xa_name], [address], [phone], [email], [status], [created_at], [update_at], [deleted], [cccd], [image], [password]) VALUES (N'7ea03b5d-216b-411b-97b5-9ac5413c19bc', 4, N'NV01', N'Vũ Thị Trang', 0, CAST(N'2003-09-13T00:00:00.000' AS DateTime), NULL, NULL, NULL, NULL, NULL, NULL, N'Thái Bình', N'0368423642', N'trang@gmail.com          ', 0, NULL, NULL, 1, N'034382462642', N'face12.jpg', NULL)
GO
INSERT [dbo].[employee] ([id], [id_roles], [code], [name], [gender], [birth_date], [thanh_pho], [thanh_pho_name], [huyen], [huyen_name], [xa], [xa_name], [address], [phone], [email], [status], [created_at], [update_at], [deleted], [cccd], [image], [password]) VALUES (N'd46506b4-6de3-41d7-8459-a77cbd701dd4', 5, N'NV01', N'Lâm Văn Huy', 0, CAST(N'2002-02-12T00:00:00.000' AS DateTime), NULL, NULL, NULL, NULL, NULL, NULL, N'Hà Nội', N'0328426244', N'chuc@gmail.com           ', 0, NULL, NULL, 1, N'034284263462', N'face13.jpg', NULL)
GO
INSERT [dbo].[employee] ([id], [id_roles], [code], [name], [gender], [birth_date], [thanh_pho], [thanh_pho_name], [huyen], [huyen_name], [xa], [xa_name], [address], [phone], [email], [status], [created_at], [update_at], [deleted], [cccd], [image], [password]) VALUES (N'3bbd0fd3-b64a-46cb-bbcf-a7d450867eab', 6, N'NV01', N'Vũ Thị Ngô', 0, CAST(N'2000-01-08T00:00:00.000' AS DateTime), NULL, NULL, NULL, NULL, NULL, NULL, N'Hà Nội', N'0348235262', N'ngo@gmail.com            ', 0, NULL, NULL, 1, N'034823457234', N'face14.jpg', NULL)
GO
INSERT [dbo].[employee] ([id], [id_roles], [code], [name], [gender], [birth_date], [thanh_pho], [thanh_pho_name], [huyen], [huyen_name], [xa], [xa_name], [address], [phone], [email], [status], [created_at], [update_at], [deleted], [cccd], [image], [password]) VALUES (N'0a8587f9-4c42-4cf1-afc3-b16dbf7391db', 7, N'NV01', N'Trần Quốc Khánh', 0, CAST(N'2001-02-01T00:00:00.000' AS DateTime), NULL, NULL, NULL, NULL, NULL, NULL, N'Thanh Hóa', N'0342842634', N'duyet@gmail.com          ', 0, NULL, NULL, 1, N'034382462734', N'face15.jpg', NULL)
GO
INSERT [dbo].[employee] ([id], [id_roles], [code], [name], [gender], [birth_date], [thanh_pho], [thanh_pho_name], [huyen], [huyen_name], [xa], [xa_name], [address], [phone], [email], [status], [created_at], [update_at], [deleted], [cccd], [image], [password]) VALUES (N'c7cfa9e4-47b2-4aed-8f17-c530dea374b8', 8, N'NV01', N'Nguyễn Khánh Cường', 0, CAST(N'1995-01-01T00:00:00.000' AS DateTime), NULL, NULL, NULL, NULL, NULL, NULL, N'Hà Giang', N'0357326242', N'ha@gmail.com             ', 0, NULL, NULL, 1, N'034832472342', N'face16.jpg', NULL)
GO
INSERT [dbo].[employee] ([id], [id_roles], [code], [name], [gender], [birth_date], [thanh_pho], [thanh_pho_name], [huyen], [huyen_name], [xa], [xa_name], [address], [phone], [email], [status], [created_at], [update_at], [deleted], [cccd], [image], [password]) VALUES (N'b2599c14-53a3-45f8-a24d-e5080f38281a', 5, N'NV01', N'Nguyễn Quỳnh Anh', 0, CAST(N'2002-12-12T00:00:00.000' AS DateTime), NULL, NULL, NULL, NULL, NULL, NULL, N'Bắc Giang', N'0343247246', N'bap@gmail.com            ', 0, NULL, NULL, 1, N'034284274234', N'face17.jpg', NULL)
GO
SET IDENTITY_INSERT [dbo].[brand] ON 
GO
INSERT [dbo].[brand] ([id], [code], [name], [created_at], [updated_at], [status], [deleted]) VALUES (0, N'Jacquelin', N'Bashford', NULL, NULL, 0, 0)
GO
INSERT [dbo].[brand] ([id], [code], [name], [created_at], [updated_at], [status], [deleted]) VALUES (1, N'Nady', N'McGilbon', NULL, NULL, 0, 0)
GO
INSERT [dbo].[brand] ([id], [code], [name], [created_at], [updated_at], [status], [deleted]) VALUES (2, N'Umeko', N'Marshal', NULL, NULL, 0, 0)
GO
INSERT [dbo].[brand] ([id], [code], [name], [created_at], [updated_at], [status], [deleted]) VALUES (3, N'Tobe', N'Ingham', NULL, NULL, 0, 0)
GO
INSERT [dbo].[brand] ([id], [code], [name], [created_at], [updated_at], [status], [deleted]) VALUES (4, N'Klement', N'Awton', NULL, NULL, 0, 0)
GO
INSERT [dbo].[brand] ([id], [code], [name], [created_at], [updated_at], [status], [deleted]) VALUES (5, N'Del', N'Foyston', NULL, NULL, 0, 0)
GO
INSERT [dbo].[brand] ([id], [code], [name], [created_at], [updated_at], [status], [deleted]) VALUES (6, N'Edwina', N'Douty', NULL, NULL, 0, 0)
GO
INSERT [dbo].[brand] ([id], [code], [name], [created_at], [updated_at], [status], [deleted]) VALUES (7, N'Huberto', N'Balog', NULL, NULL, 0, 0)
GO
INSERT [dbo].[brand] ([id], [code], [name], [created_at], [updated_at], [status], [deleted]) VALUES (8, N'Gay', N'Grunwald', NULL, NULL, 0, 0)
GO
INSERT [dbo].[brand] ([id], [code], [name], [created_at], [updated_at], [status], [deleted]) VALUES (9, N'Herby', N'Crissil', NULL, NULL, 0, 0)
GO
INSERT [dbo].[brand] ([id], [code], [name], [created_at], [updated_at], [status], [deleted]) VALUES (10, N'Asa', N'Brumpton', NULL, NULL, 0, 0)
GO
INSERT [dbo].[brand] ([id], [code], [name], [created_at], [updated_at], [status], [deleted]) VALUES (11, N'Edin', N'Jiran', NULL, NULL, 0, 0)
GO
INSERT [dbo].[brand] ([id], [code], [name], [created_at], [updated_at], [status], [deleted]) VALUES (12, N'Lucretia', N'Dormand', NULL, NULL, 0, 0)
GO
INSERT [dbo].[brand] ([id], [code], [name], [created_at], [updated_at], [status], [deleted]) VALUES (13, N'Aubrey', N'Woodyear', NULL, NULL, 0, 0)
GO
INSERT [dbo].[brand] ([id], [code], [name], [created_at], [updated_at], [status], [deleted]) VALUES (14, N'Marina', N'Crampin', NULL, NULL, 0, 0)
GO
INSERT [dbo].[brand] ([id], [code], [name], [created_at], [updated_at], [status], [deleted]) VALUES (15, N'Cesaro', N'Applegarth', NULL, NULL, 0, 0)
GO
INSERT [dbo].[brand] ([id], [code], [name], [created_at], [updated_at], [status], [deleted]) VALUES (16, N'Kessiah', N'Dozdill', NULL, NULL, 0, 0)
GO
INSERT [dbo].[brand] ([id], [code], [name], [created_at], [updated_at], [status], [deleted]) VALUES (17, N'Patin', N'Maddyson', NULL, NULL, 0, 0)
GO
INSERT [dbo].[brand] ([id], [code], [name], [created_at], [updated_at], [status], [deleted]) VALUES (18, N'Kissee', N'Cornewell', NULL, NULL, 0, 0)
GO
INSERT [dbo].[brand] ([id], [code], [name], [created_at], [updated_at], [status], [deleted]) VALUES (19, N'Melody', N'Hackly', NULL, NULL, 0, 0)
GO
SET IDENTITY_INSERT [dbo].[brand] OFF
GO
SET IDENTITY_INSERT [dbo].[color] ON 
GO
INSERT [dbo].[color] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (1, N'Lonnard', N'Ferrari', NULL, NULL, 0, 0)
GO
INSERT [dbo].[color] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (2, N'Javier', N'Manolov', NULL, NULL, 0, 0)
GO
INSERT [dbo].[color] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (3, N'Marybelle', N'Coakley', NULL, NULL, 0, 0)
GO
INSERT [dbo].[color] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (4, N'Maria', N'Kuzemka', NULL, NULL, 0, 0)
GO
INSERT [dbo].[color] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (5, N'Bernadina', N'Lippett', NULL, NULL, 0, 0)
GO
INSERT [dbo].[color] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (6, N'Hamel', N'Blaine', NULL, NULL, 0, 0)
GO
INSERT [dbo].[color] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (7, N'Kile', N'Offa', NULL, NULL, 0, 0)
GO
INSERT [dbo].[color] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (8, N'Stacia', N'Wallbutton', NULL, NULL, 0, 0)
GO
INSERT [dbo].[color] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (9, N'Franzen', N'Mangan', NULL, NULL, 0, 0)
GO
INSERT [dbo].[color] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (10, N'Karlyn', N'Murrhaupt', NULL, NULL, 0, 0)
GO
INSERT [dbo].[color] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (11, N'Ulrica', N'Piner', NULL, NULL, 0, 0)
GO
INSERT [dbo].[color] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (12, N'Melessa', N'Skune', NULL, NULL, 0, 0)
GO
INSERT [dbo].[color] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (13, N'Jana', N'Wyness', NULL, NULL, 0, 0)
GO
INSERT [dbo].[color] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (14, N'Tedda', N'Johnsee', NULL, NULL, 0, 0)
GO
INSERT [dbo].[color] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (15, N'Kathie', N'Glaze', NULL, NULL, 0, 0)
GO
INSERT [dbo].[color] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (16, N'Kalina', N'Musk', NULL, NULL, 0, 0)
GO
INSERT [dbo].[color] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (17, N'Neall', N'Figliovanni', NULL, NULL, 0, 0)
GO
INSERT [dbo].[color] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (18, N'Orazio', N'Rylance', NULL, NULL, 0, 0)
GO
INSERT [dbo].[color] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (19, N'Auberon', N'Jessel', NULL, NULL, 0, 0)
GO
INSERT [dbo].[color] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (20, N'Sigfrid', N'MacNeachtain', NULL, NULL, 0, 0)
GO
SET IDENTITY_INSERT [dbo].[color] OFF
GO
SET IDENTITY_INSERT [dbo].[material] ON 
GO
INSERT [dbo].[material] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (0, N'Eduard                   ', N'Blackbrough', NULL, NULL, 0, 0)
GO
INSERT [dbo].[material] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (1, N'Damita                   ', N'Sharrock', NULL, NULL, 0, 0)
GO
INSERT [dbo].[material] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (2, N'Fionnula                 ', N'Poyser', NULL, NULL, 0, 0)
GO
INSERT [dbo].[material] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (3, N'Pincas                   ', N'Maneylaws', NULL, NULL, 0, 0)
GO
INSERT [dbo].[material] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (4, N'Teador                   ', N'Stode', NULL, NULL, 0, 0)
GO
INSERT [dbo].[material] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (5, N'Maryjo                   ', N'Creed', NULL, NULL, 0, 0)
GO
INSERT [dbo].[material] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (6, N'Jill                     ', N'Camelin', NULL, NULL, 0, 0)
GO
INSERT [dbo].[material] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (7, N'Abeu                     ', N'Pervew', NULL, NULL, 0, 0)
GO
INSERT [dbo].[material] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (8, N'Kenton                   ', N'Arboine', NULL, NULL, 0, 0)
GO
INSERT [dbo].[material] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (9, N'Elly                     ', N'Peschet', NULL, NULL, 0, 0)
GO
INSERT [dbo].[material] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (10, N'Mayer                    ', N'Yokelman', NULL, NULL, 0, 0)
GO
INSERT [dbo].[material] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (11, N'Nataniel                 ', N'Cardinale', NULL, NULL, 0, 0)
GO
INSERT [dbo].[material] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (12, N'Salomon                  ', N'Pineaux', NULL, NULL, 0, 0)
GO
INSERT [dbo].[material] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (13, N'Gerrard                  ', N'Ebertz', NULL, NULL, 0, 0)
GO
INSERT [dbo].[material] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (14, N'Aleksandr                ', N'Kilgallen', NULL, NULL, 0, 0)
GO
INSERT [dbo].[material] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (15, N'Lem                      ', N'Clerc', NULL, NULL, 0, 0)
GO
INSERT [dbo].[material] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (16, N'Jo                       ', N'Marklow', NULL, NULL, 0, 0)
GO
INSERT [dbo].[material] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (17, N'Katerine                 ', N'Blacklidge', NULL, NULL, 0, 0)
GO
INSERT [dbo].[material] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (18, N'Annmarie                 ', N'Cranstone', NULL, NULL, 0, 0)
GO
INSERT [dbo].[material] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (19, N'Rutger                   ', N'Huetson', NULL, NULL, 0, 0)
GO
SET IDENTITY_INSERT [dbo].[material] OFF
GO
SET IDENTITY_INSERT [dbo].[origin] ON 
GO
INSERT [dbo].[origin] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (0, N'Isaiah', N'Bisco', NULL, NULL, 0, 0)
GO
INSERT [dbo].[origin] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (1, N'Neils', N'O''Duilleain', NULL, NULL, 0, 0)
GO
INSERT [dbo].[origin] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (2, N'Wilden', N'Aingell', NULL, NULL, 0, 0)
GO
INSERT [dbo].[origin] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (3, N'Tory', N'Dottridge', NULL, NULL, 0, 0)
GO
INSERT [dbo].[origin] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (4, N'Nicole', N'Robertazzi', NULL, NULL, 0, 0)
GO
INSERT [dbo].[origin] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (5, N'Esmaria', N'Haug', NULL, NULL, 0, 0)
GO
INSERT [dbo].[origin] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (6, N'Abigael', N'Redmore', NULL, NULL, 0, 0)
GO
INSERT [dbo].[origin] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (7, N'Sophi', N'Vanyashkin', NULL, NULL, 0, 0)
GO
INSERT [dbo].[origin] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (8, N'Ingemar', N'Welland', NULL, NULL, 0, 0)
GO
INSERT [dbo].[origin] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (9, N'Holly', N'Ambrosini', NULL, NULL, 0, 0)
GO
INSERT [dbo].[origin] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (10, N'Colin', N'Haslum', NULL, NULL, 0, 0)
GO
INSERT [dbo].[origin] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (11, N'Daveen', N'Lathleiff', NULL, NULL, 0, 0)
GO
INSERT [dbo].[origin] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (12, N'Winn', N'Sparwell', NULL, NULL, 0, 0)
GO
INSERT [dbo].[origin] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (13, N'Gretchen', N'Somerled', NULL, NULL, 0, 0)
GO
INSERT [dbo].[origin] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (14, N'Christalle', N'Lambin', NULL, NULL, 0, 0)
GO
INSERT [dbo].[origin] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (15, N'Cullin', N'Plunket', NULL, NULL, 0, 0)
GO
INSERT [dbo].[origin] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (16, N'Ashlan', N'King', NULL, NULL, 0, 0)
GO
INSERT [dbo].[origin] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (17, N'Lyndsie', N'Morewood', NULL, NULL, 0, 0)
GO
INSERT [dbo].[origin] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (18, N'Marylinda', N'Garlick', NULL, NULL, 0, 0)
GO
INSERT [dbo].[origin] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (19, N'Alfi', N'Francklyn', NULL, NULL, 0, 0)
GO
SET IDENTITY_INSERT [dbo].[origin] OFF
GO
SET IDENTITY_INSERT [dbo].[pattern] ON 
GO
INSERT [dbo].[pattern] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (0, N'Kristofer', N'Stratz', NULL, NULL, 0, 0)
GO
INSERT [dbo].[pattern] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (1, N'Bendix', N'Tupie', NULL, NULL, 0, 0)
GO
INSERT [dbo].[pattern] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (2, N'Norri', N'Vinden', NULL, NULL, 0, 0)
GO
INSERT [dbo].[pattern] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (3, N'Gavin', N'Blakeney', NULL, NULL, 0, 0)
GO
INSERT [dbo].[pattern] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (4, N'Reagen', N'Durnian', NULL, NULL, 0, 0)
GO
INSERT [dbo].[pattern] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (5, N'Carol', N'Warke', NULL, NULL, 0, 0)
GO
INSERT [dbo].[pattern] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (6, N'Carmelle', N'Woodruff', NULL, NULL, 0, 0)
GO
INSERT [dbo].[pattern] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (7, N'Honoria', N'Motherwell', NULL, NULL, 0, 0)
GO
INSERT [dbo].[pattern] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (8, N'Kirby', N'Fahy', NULL, NULL, 0, 0)
GO
INSERT [dbo].[pattern] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (9, N'Clarie', N'Milbourn', NULL, NULL, 0, 0)
GO
INSERT [dbo].[pattern] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (10, N'Cassie', N'Antoons', NULL, NULL, 0, 0)
GO
INSERT [dbo].[pattern] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (11, N'Guy', N'Hubback', NULL, NULL, 0, 0)
GO
INSERT [dbo].[pattern] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (12, N'Almira', N'Dreye', NULL, NULL, 0, 0)
GO
INSERT [dbo].[pattern] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (13, N'Torie', N'Vinter', NULL, NULL, 0, 0)
GO
INSERT [dbo].[pattern] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (14, N'Kit', N'Gowland', NULL, NULL, 0, 0)
GO
INSERT [dbo].[pattern] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (15, N'Oona', N'Emloch', NULL, NULL, 0, 0)
GO
INSERT [dbo].[pattern] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (16, N'Bronson', N'Sandhill', NULL, NULL, 0, 0)
GO
INSERT [dbo].[pattern] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (17, N'Dottie', N'Schottli', NULL, NULL, 0, 0)
GO
INSERT [dbo].[pattern] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (18, N'Kerrin', N'Tibbs', NULL, NULL, 0, 0)
GO
INSERT [dbo].[pattern] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (19, N'Robinetta', N'Fattorini', NULL, NULL, 0, 0)
GO
SET IDENTITY_INSERT [dbo].[pattern] OFF
GO
SET IDENTITY_INSERT [dbo].[product] ON 
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (0, N'Alexia', N'Konig', 0, NULL, NULL, 0)
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (1, N'Jaclyn', N'Barends', 0, NULL, NULL, 0)
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (2, N'Ruthe', N'Beecker', 0, NULL, NULL, 0)
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (3, N'Claudell', N'Bowling', 0, NULL, NULL, 0)
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (4, N'Rasizello', N'Cowx', 0, NULL, NULL, 0)
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (5, N'Issi', N'Wakeford', 0, NULL, NULL, 0)
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (6, N'Annadiana', N'Yardley', 0, NULL, NULL, 0)
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (7, N'Gisella', N'Clemon', 0, NULL, NULL, 0)
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (8, N'Brana', N'McGettigan', 0, NULL, NULL, 0)
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (9, N'El', N'Mart', 0, NULL, NULL, 0)
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (10, N'Ailene', N'Ingerfield', 0, NULL, NULL, 0)
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (11, N'Almeta', N'Canepe', 0, NULL, NULL, 0)
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (12, N'Frederigo', N'Dovydenas', 0, NULL, NULL, 0)
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (13, N'Tybalt', N'Chivers', 0, NULL, NULL, 0)
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (14, N'Giovanna', N'Woollends', 0, NULL, NULL, 0)
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (15, N'Chantalle', N'Mercik', 0, NULL, NULL, 0)
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (16, N'Alexandre', N'Spray', 0, NULL, NULL, 0)
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (17, N'Anabal', N'Kleinhausen', 0, NULL, NULL, 0)
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (18, N'Tania', N'Sterley', 0, NULL, NULL, 0)
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (19, N'Goddart', N'Diche', 0, NULL, NULL, 0)
GO
SET IDENTITY_INSERT [dbo].[product] OFF
GO
SET IDENTITY_INSERT [dbo].[styles] ON 
GO
INSERT [dbo].[styles] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (0, N'Marchelle', N'Sebrook', NULL, NULL, 0, 0)
GO
INSERT [dbo].[styles] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (1, N'Mace', N'Lacelett', NULL, NULL, 0, 0)
GO
INSERT [dbo].[styles] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (2, N'Ezra', N'Gwyllt', NULL, NULL, 0, 0)
GO
INSERT [dbo].[styles] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (3, N'Marcelline', N'Plues', NULL, NULL, 0, 0)
GO
INSERT [dbo].[styles] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (4, N'Magnum', N'Curley', NULL, NULL, 0, 0)
GO
INSERT [dbo].[styles] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (5, N'Dilly', N'Ellcome', NULL, NULL, 0, 0)
GO
INSERT [dbo].[styles] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (6, N'Darelle', N'Stapele', NULL, NULL, 0, 0)
GO
INSERT [dbo].[styles] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (7, N'Cordie', N'Hedden', NULL, NULL, 0, 0)
GO
INSERT [dbo].[styles] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (8, N'Eleni', N'Hartles', NULL, NULL, 0, 0)
GO
INSERT [dbo].[styles] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (9, N'Rachelle', N'Balaam', NULL, NULL, 0, 0)
GO
INSERT [dbo].[styles] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (10, N'Christalle', N'Turfin', NULL, NULL, 0, 0)
GO
INSERT [dbo].[styles] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (11, N'Westleigh', N'Augustin', NULL, NULL, 0, 0)
GO
INSERT [dbo].[styles] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (12, N'Ermin', N'Bruins', NULL, NULL, 0, 0)
GO
INSERT [dbo].[styles] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (13, N'Danielle', N'Pennuzzi', NULL, NULL, 0, 0)
GO
INSERT [dbo].[styles] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (14, N'Gilly', N'Swarbrick', NULL, NULL, 0, 0)
GO
INSERT [dbo].[styles] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (15, N'Con', N'Duchenne', NULL, NULL, 0, 0)
GO
INSERT [dbo].[styles] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (16, N'Boniface', N'Edkins', NULL, NULL, 0, 0)
GO
INSERT [dbo].[styles] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (17, N'Sonia', N'Emmot', NULL, NULL, 0, 0)
GO
INSERT [dbo].[styles] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (18, N'Franciska', N'Fassum', NULL, NULL, 0, 0)
GO
INSERT [dbo].[styles] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (19, N'Gar', N'Feyer', NULL, NULL, 0, 0)
GO
SET IDENTITY_INSERT [dbo].[styles] OFF
GO
SET IDENTITY_INSERT [dbo].[size] ON 
GO
INSERT [dbo].[size] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (0, N'XL', N'XL', NULL, NULL, 0, 0)
GO
INSERT [dbo].[size] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (1, N'3XL', N'M', NULL, NULL, 0, 0)
GO
INSERT [dbo].[size] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (2, N'M', N'2XL', NULL, NULL, 0, 0)
GO
INSERT [dbo].[size] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (3, N'S', N'S', NULL, NULL, 0, 0)
GO
INSERT [dbo].[size] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (4, N'2XL', N'L', NULL, NULL, 0, 0)
GO
INSERT [dbo].[size] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (5, N'M', N'2XL', NULL, NULL, 0, 0)
GO
INSERT [dbo].[size] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (6, N'XS', N'XL', NULL, NULL, 0, 0)
GO
INSERT [dbo].[size] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (7, N'S', N'XS', NULL, NULL, 0, 0)
GO
INSERT [dbo].[size] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (8, N'XS', N'M', NULL, NULL, 0, 0)
GO
INSERT [dbo].[size] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (9, N'M', N'L', NULL, NULL, 0, 0)
GO
SET IDENTITY_INSERT [dbo].[size] OFF
GO
SET IDENTITY_INSERT [dbo].[voucher] ON 
GO
INSERT [dbo].[voucher] ([id], [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount], [min_order]) VALUES (1, N'T10N2023CASHSFCE', N'Sample Voucher', CAST(N'2023-10-06T10:00:00.000' AS DateTime), CAST(N'2023-10-20T14:30:00.000' AS DateTime), 50, 10.5, 1, CAST(N'2023-10-06T08:00:00.000' AS DateTime), CAST(N'2023-10-06T08:30:00.000' AS DateTime), 0, 0, NULL, NULL)
GO
INSERT [dbo].[voucher] ([id], [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount], [min_order]) VALUES (2, N'T10N2023CASHGJOI', N'Voucher 1', CAST(N'2023-10-05T15:53:12.633' AS DateTime), CAST(N'2023-11-04T15:53:12.633' AS DateTime), 1, 8.1120415473449317, 0, CAST(N'2023-10-06T15:53:12.633' AS DateTime), CAST(N'2023-10-06T15:53:12.633' AS DateTime), 0, 0, NULL, NULL)
GO
INSERT [dbo].[voucher] ([id], [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount], [min_order]) VALUES (3, N'T10N2023CASHYXSA', N'Voucher 2', CAST(N'2023-10-04T15:53:12.633' AS DateTime), CAST(N'2023-11-03T15:53:12.633' AS DateTime), 58, 48.841095510312435, 1, CAST(N'2023-10-06T15:53:12.633' AS DateTime), CAST(N'2023-10-06T15:53:12.633' AS DateTime), 0, 0, NULL, NULL)
GO
INSERT [dbo].[voucher] ([id], [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount], [min_order]) VALUES (4, N'T10N2023PERHCRC', N'Voucher 3', CAST(N'2023-10-03T15:53:12.633' AS DateTime), CAST(N'2023-11-02T15:53:12.633' AS DateTime), 67, 31.027456496898747, 0, CAST(N'2023-10-06T15:53:12.633' AS DateTime), CAST(N'2023-10-06T15:53:12.633' AS DateTime), 1, 0, NULL, NULL)
GO
INSERT [dbo].[voucher] ([id], [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount], [min_order]) VALUES (5, N'T10N2023CASHFIOF', N'Voucher 4', CAST(N'2023-10-02T15:53:12.637' AS DateTime), CAST(N'2023-11-01T15:53:12.637' AS DateTime), 93, 3.6055725584942704, 1, CAST(N'2023-10-06T15:53:12.637' AS DateTime), CAST(N'2023-10-06T15:53:12.637' AS DateTime), 0, 1, NULL, NULL)
GO
INSERT [dbo].[voucher] ([id], [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount], [min_order]) VALUES (6, N'T10N2023CASHGXZM', N'Voucher 5', CAST(N'2023-10-01T15:53:12.637' AS DateTime), CAST(N'2023-10-31T15:53:12.637' AS DateTime), 21, 8.0769658660361454, 0, CAST(N'2023-10-06T15:53:12.637' AS DateTime), CAST(N'2023-10-06T15:53:12.637' AS DateTime), 0, 0, NULL, NULL)
GO
INSERT [dbo].[voucher] ([id], [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount], [min_order]) VALUES (7, N'T10N2023PERPFUG', N'Voucher 6', CAST(N'2023-09-30T15:53:12.637' AS DateTime), CAST(N'2023-10-30T15:53:12.637' AS DateTime), 21, 23.798123720553196, 1, CAST(N'2023-10-06T15:53:12.637' AS DateTime), CAST(N'2023-10-06T15:53:12.637' AS DateTime), 1, 0, NULL, NULL)
GO
INSERT [dbo].[voucher] ([id], [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount], [min_order]) VALUES (8, N'T10N2023CASHJLIC', N'Voucher 7', CAST(N'2023-09-29T15:53:12.637' AS DateTime), CAST(N'2023-10-29T15:53:12.637' AS DateTime), 12, 7.2298180187032157, 0, CAST(N'2023-10-06T15:53:12.637' AS DateTime), CAST(N'2023-10-06T15:53:12.637' AS DateTime), 0, 0, NULL, NULL)
GO
INSERT [dbo].[voucher] ([id], [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount], [min_order]) VALUES (9, N'T10N2023CASHZAHV', N'Voucher 8', CAST(N'2023-09-28T15:53:12.637' AS DateTime), CAST(N'2023-10-28T15:53:12.637' AS DateTime), 45, 21.563865785576272, 1, CAST(N'2023-10-06T15:53:12.637' AS DateTime), CAST(N'2023-10-06T15:53:12.637' AS DateTime), 0, 1, NULL, NULL)
GO
INSERT [dbo].[voucher] ([id], [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount], [min_order]) VALUES (10, N'T10N2023PERNSKV', N'Voucher 9', CAST(N'2023-09-27T15:53:12.637' AS DateTime), CAST(N'2023-10-27T15:53:12.637' AS DateTime), 79, 1.511227403979865, 0, CAST(N'2023-10-06T15:53:12.637' AS DateTime), CAST(N'2023-10-06T15:53:12.637' AS DateTime), 1, 0, NULL, NULL)
GO
INSERT [dbo].[voucher] ([id], [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount], [min_order]) VALUES (11, N'T10N2023CASHZLQG', N'Voucher 10', CAST(N'2023-09-26T15:53:12.637' AS DateTime), CAST(N'2023-10-26T15:53:12.637' AS DateTime), 33, 4.3247837587917957, 1, CAST(N'2023-10-06T15:53:12.637' AS DateTime), CAST(N'2023-10-06T15:53:12.637' AS DateTime), 0, 0, NULL, NULL)
GO
INSERT [dbo].[voucher] ([id], [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount], [min_order]) VALUES (12, NULL, N'VOUCHERXX3', CAST(N'2023-10-18T19:25:00.000' AS DateTime), CAST(N'2023-10-26T19:25:00.000' AS DateTime), 112, 13, 1, NULL, NULL, NULL, NULL, NULL, NULL)
GO
INSERT [dbo].[voucher] ([id], [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount], [min_order]) VALUES (13, NULL, N'VOUCHERXX3', CAST(N'2023-10-30T10:15:00.000' AS DateTime), CAST(N'2023-11-24T10:15:00.000' AS DateTime), 1213, 1232, 1, NULL, NULL, NULL, NULL, NULL, NULL)
GO
INSERT [dbo].[voucher] ([id], [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount], [min_order]) VALUES (14, NULL, N'vouchex', CAST(N'2023-11-11T08:44:00.000' AS DateTime), CAST(N'2023-10-07T08:44:00.000' AS DateTime), 12, 12, 1, NULL, NULL, NULL, NULL, NULL, NULL)
GO
INSERT [dbo].[voucher] ([id], [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount], [min_order]) VALUES (15, NULL, N'efwef', CAST(N'2023-10-07T13:47:00.000' AS DateTime), CAST(N'2023-10-07T08:52:00.000' AS DateTime), 12, 12, 1, NULL, NULL, NULL, NULL, NULL, NULL)
GO
INSERT [dbo].[voucher] ([id], [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount], [min_order]) VALUES (16, NULL, N'efweffesize', CAST(N'2023-10-07T13:47:00.000' AS DateTime), CAST(N'2023-10-07T08:52:00.000' AS DateTime), 12, 12, 1, NULL, NULL, NULL, NULL, NULL, NULL)
GO
INSERT [dbo].[voucher] ([id], [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount], [min_order]) VALUES (17, NULL, N'VOUCHERXX3', CAST(N'2023-10-07T09:50:00.000' AS DateTime), CAST(N'2023-10-07T09:50:00.000' AS DateTime), 1213, 1232, 1, NULL, NULL, NULL, NULL, NULL, NULL)
GO
INSERT [dbo].[voucher] ([id], [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount], [min_order]) VALUES (18, NULL, N'efweffeaef', CAST(N'2023-10-07T09:05:00.000' AS DateTime), CAST(N'2023-10-07T09:05:00.000' AS DateTime), 12, 14, 1, NULL, NULL, NULL, NULL, NULL, NULL)
GO
INSERT [dbo].[voucher] ([id], [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount], [min_order]) VALUES (19, NULL, N'efwef', CAST(N'2023-10-07T22:25:00.000' AS DateTime), CAST(N'2023-10-07T22:25:00.000' AS DateTime), 12, 17, 0, NULL, NULL, NULL, NULL, NULL, NULL)
GO
INSERT [dbo].[voucher] ([id], [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount], [min_order]) VALUES (20, NULL, N'efwef', NULL, NULL, 14, 14, 1, NULL, NULL, NULL, NULL, NULL, NULL)
GO
SET IDENTITY_INSERT [dbo].[voucher] OFF
GO

insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Regan', 27404, 1537, 19, 16, 16, 9, 13, 5, 6, 2, 'a75079ed80fd03f4781bebe3b33206bb8ae225e6');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Selinda', 40251, 1729, 18, 13, 2, 4, 7, 7, 5, 4, '75d816fc4965ca482852fd71610300d3f19a71cb');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Pavel', 33470, 360, 19, 1, 17, 8, 10, 9, 17, 14, '7dba14e866eec470ada6bd89bff0bf4574249e8c');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Beckie', 21136, 20, 2, 1, 19, 16, 8, 7, 9, 8, '21b52b203d80c834912c9a48b52040e5cf3d776d');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Dag', 16551, 1529, 19, 12, 10, 7, 15, 9, 7, 15, 'd272050ab4b6a799b96d9e8ae6e4e6365e86dc58');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Pierrette', 39664, 1220, 3, 10, 11, 15, 18, 2, 8, 2, 'd662d11e382992c968db51f71c561e55ffa6d001');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Hodge', 12881, 1160, 6, 5, 10, 6, 17, 3, 14, 11, 'd077b886626e5a001b5f31defb05bc2b6e3b6dea');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Berky', 27269, 1099, 16, 15, 16, 9, 14, 2, 18, 18, '209c67d682928e2ec78ce691687471d275c6cf94');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Alister', 37281, 1855, 7, 15, 17, 19, 6, 5, 12, 10, 'b9698f554043bcb1a4e367a907ca876df75e57e0');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Abel', 13883, 1905, 1, 7, 15, 14, 11, 5, 6, 5, 'a38bacc3284210363d6d5d72beedbfdc8ce306ac');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Brianne', 33137, 321, 5, 4, 11, 2, 7, 4, 19, 10, '756f5d1fec0deabf64ac3c89672548d7ba07f9a7');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Gram', 39913, 102, 5, 12, 14, 11, 4, 0, 13, 9, 'b3c3ced561c1a905ac880b3582528d4ed604cbd3');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Aguie', 11612, 1532, 17, 14, 11, 2, 3, 5, 8, 7, '2b286b4b7980dcf7e5f8c6e5a651c089ec672e89');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Austin', 25370, 2011, 17, 1, 8, 13, 5, 6, 19, 17, '013bd747bbf3e5408dc3042042f00e7dfa78918a');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Hedda', 26127, 797, 5, 17, 12, 9, 19, 5, 6, 18, '8ac80b6370d14f16d1ed8b3479082c6ec08a0338');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Conn', 25812, 437, 5, 19, 8, 4, 17, 5, 3, 12, '3b919c63c04e1742813a4b57678aec29c9275fe8');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Agustin', 23419, 2146, 1, 16, 19, 6, 8, 7, 1, 14, '8c3eed498387113a45a3980b4cedcbe87db5e203');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Marie-jeanne', 42775, 396, 8, 19, 3, 2, 16, 9, 6, 13, 'c9c6972c9f930e95ea5262ddf8f95493029a701f');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Elladine', 32831, 1166, 3, 2, 18, 15, 8, 1, 14, 17, '14853fe52ba671492bde6507f86642234db27509');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Lutero', 18599, 2291, 3, 17, 15, 9, 19, 8, 16, 7, 'e46a9f57cec270a8c7f2ee03c8dde85e605347b4');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Errick', 48344, 1127, 14, 7, 7, 13, 13, 9, 10, 11, '6322fbbb93122050e76553f0df325f271ad8e8f8');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Parnell', 19907, 1404, 11, 15, 12, 19, 3, 4, 6, 7, '0668f7fb0014183efc70f1bd5069fc252f2b8ea9');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Humfrid', 33735, 370, 5, 13, 11, 15, 17, 0, 10, 16, '9f54d28a2e8240ac797ad031f83bd7a9fb74b0a8');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Dore', 42905, 1968, 7, 4, 11, 8, 14, 1, 9, 9, '3bad3ec71cd0c3d29a2c5ac2fa7f205e6b042e46');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Catrina', 17477, 1529, 10, 4, 1, 9, 6, 0, 4, 11, 'ca8f4944311b5a62e5a0ce96ebde4ef09f0f0ff4');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Meredith', 30271, 13, 10, 15, 14, 4, 8, 9, 8, 19, 'd2a5150807fea260b25c70aef77c142544b0847f');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Prent', 19669, 2167, 8, 6, 4, 8, 3, 10, 13, 18, '4219135330b08fd3ec1ebefac531e314e43ea04d');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Damaris', 30089, 1823, 5, 17, 6, 14, 7, 8, 16, 4, '3d8add022a9d345f29e9740995910a3a591d56fe');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Keary', 38907, 1868, 12, 10, 2, 19, 6, 4, 12, 17, '925f5aab9fe575301eefd3e70ee5cc383cb32ca0');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Tybalt', 48831, 272, 18, 19, 13, 2, 16, 6, 15, 14, 'c523ed3f2cba10df4a5adf0c677f2b8fb04ebb11');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Elyssa', 17722, 1220, 12, 13, 11, 8, 4, 4, 7, 18, 'a8e2eb1d90e1f4d549ab8fbcd796d524395b3dfe');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Bertha', 14261, 1798, 5, 14, 3, 8, 16, 5, 2, 15, '5bf0c422a3b23bfc848d785416cec097a1406bd0');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Netti', 42664, 1929, 2, 10, 7, 12, 2, 8, 3, 15, '0ca9c7e9178af09456513882818ea1baaf3eb0a0');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Nell', 42712, 318, 8, 10, 9, 2, 18, 5, 19, 3, '82c7e680134d070d55989f4d22191fec92e3b272');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Nehemiah', 26262, 418, 2, 14, 9, 17, 6, 7, 15, 16, 'dbd415ee04b8ce06988bf8991b72294f06e0b898');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Mikel', 48887, 1699, 9, 18, 16, 11, 17, 2, 6, 9, 'f1b364c5be607af27e34191848a096999ddf7cf3');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Fabio', 11453, 1123, 12, 9, 3, 3, 6, 9, 14, 8, '62a61f725459a464f82f17c9dfc94eaa40220e59');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Floris', 13592, 904, 19, 2, 6, 5, 17, 8, 4, 12, '96053a6941bba5e5972ff85a403ad6dd50acecdc');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Lotti', 12690, 155, 3, 14, 17, 11, 18, 4, 15, 3, 'bd55ee90e2c782fa41d22972a6b070e1fbb9dd9b');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Branden', 35243, 1964, 2, 17, 9, 7, 7, 8, 8, 3, '213180204049ce9981ed749538196153b8be5811');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Lauritz', 34772, 1866, 12, 6, 11, 10, 7, 5, 8, 4, '33d99ecf632388d8faef162d094c147485a96f3b');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Clareta', 25016, 474, 15, 4, 19, 16, 1, 0, 9, 16, '965b7c6e8f415e1334161e38da077609aa89d621');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Ephraim', 21800, 1035, 9, 12, 12, 5, 18, 4, 6, 17, '976bca78e814febd77f56cbb1979e69fc6f8ed08');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Theressa', 14337, 1853, 13, 16, 12, 15, 19, 7, 13, 4, '40658d20e99d36860775525c1a5d81fa5edfaf2a');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Lannie', 24465, 2061, 8, 15, 17, 14, 5, 1, 11, 5, '9626d05bd6645124ed2449b4f63c64407b915ae8');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Mischa', 23783, 1017, 9, 14, 2, 11, 7, 1, 12, 5, 'd1481927cf47b0d7b00682e8872a8f0d30105e67');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Muire', 48243, 2107, 15, 13, 13, 8, 13, 9, 10, 16, '19d561c9bf7177cdc6d4f26932bd02906635dec6');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Ogdon', 22564, 127, 14, 6, 6, 4, 14, 2, 2, 15, '1b9c10f7df52af97b2074527580e7b3de706e95f');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Arlana', 37647, 680, 2, 11, 15, 8, 17, 0, 9, 4, 'bacd56abaf4cadf14edc52e5edee08cbe010b06b');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Nicholle', 39540, 13, 11, 11, 9, 15, 17, 10, 19, 6, '65b739cc56911fd12845fbcdded564044719f549');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Thomasin', 22382, 487, 16, 10, 6, 6, 6, 8, 14, 13, 'df1704d866fc3e96a15b3a1ec732efeacd62ab40');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Mayer', 17004, 179, 14, 19, 16, 19, 17, 0, 14, 6, 'c91a37a2511575283a69940335197b6c7b5c8e78');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Madeleine', 28184, 1984, 6, 13, 15, 14, 18, 8, 9, 19, '774bf1255382034ff5c11299e2da3ba3375adcfe');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Meyer', 34057, 1513, 11, 10, 16, 8, 15, 5, 18, 5, '0ad807be1fe7eff2c52a093e166c0b1d10c21ded');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Dorise', 32346, 636, 17, 6, 5, 15, 6, 6, 13, 9, 'abaad793b10294fa01219b288822f7dc9184ee9c');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Karel', 26315, 2073, 10, 4, 6, 14, 5, 7, 9, 14, 'e7385c0b93676eadebdce324b8cbb2e9d8abeb11');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Konstanze', 44344, 901, 13, 18, 8, 3, 13, 2, 14, 8, 'd516887e1d933cab44fbb70feecb063952ea8724');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Devy', 43061, 239, 6, 19, 15, 13, 9, 10, 17, 2, 'a2da538a8b56efaad982065de94f00a4b2a216b0');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Deva', 42511, 2264, 17, 11, 18, 2, 19, 0, 19, 12, 'adb91d41aabef231162fd4ff315f0aca998257fd');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Stern', 22188, 809, 3, 19, 7, 16, 1, 8, 9, 19, '6aa3a697e88fd1d91c682aeef88522365f2c45bf');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Tiffany', 32530, 866, 2, 5, 12, 6, 15, 8, 16, 8, '242877ab817a8a32d4185f3d8571a60feb9506f5');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Hershel', 44325, 11, 16, 19, 5, 18, 8, 10, 17, 19, '0456a586474c957041297dc2466e48d23bcfca24');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Emylee', 14033, 898, 12, 15, 5, 13, 7, 5, 16, 3, 'f2fc46b4e85a701a015ccf55622213a9d7dab845');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Hugo', 25857, 2170, 6, 17, 12, 2, 1, 3, 18, 3, '806aea1b22f6e5b0bf1c94476d53ddb5aebbd0a2');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Halie', 37872, 1504, 13, 3, 8, 5, 14, 7, 8, 15, 'd19b4f1bf8e4bbdcbd1a5db96cf35b97e29893f4');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Baxy', 23510, 211, 11, 18, 12, 7, 2, 10, 9, 16, '7f902e49d4f00adf98b9aad4ba024270662b8f93');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Olivia', 31325, 565, 2, 8, 2, 11, 1, 0, 12, 6, '5dd587f18171aa841823594d6679d6d7360ba07e');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Augustus', 13301, 1142, 9, 13, 12, 3, 16, 0, 9, 6, '525a9a5c19c656bbb76bac34c61c2c5a8a20d025');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Vernon', 44282, 108, 1, 13, 13, 6, 9, 9, 3, 4, '3987ce71d3891707e353e718abdd1185eb9b6ffe');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Audre', 35037, 1081, 12, 9, 18, 10, 11, 7, 7, 4, '7276e5489a73a4510242b3907053f811d0937f71');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Jaquenetta', 37427, 114, 19, 5, 18, 19, 6, 4, 12, 19, '6187642f8334472e465f46a38ffd3cfcb6368dea');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Flore', 19750, 1767, 13, 7, 9, 17, 16, 2, 6, 5, '5b7790e4dfc6f022a6d628d55f61fb0eea71d69a');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Yevette', 43546, 184, 6, 12, 11, 14, 5, 1, 4, 14, '7db6719154dfe4a9a2a259582d2dc962bf1a2b7d');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Bastian', 26644, 1431, 11, 15, 5, 8, 1, 5, 15, 4, '1dc6e6589f0ab88c61da0dce8b3d115374cad559');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Liza', 13297, 1119, 4, 1, 5, 15, 5, 3, 14, 6, 'e6636cd8b258554846967c1b89375b916f4b9742');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Sam', 40216, 1410, 17, 8, 2, 15, 14, 0, 14, 4, '6f7073a093bce24e296268a859c0e7b24ba28076');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Ashely', 30824, 1451, 5, 4, 2, 15, 3, 0, 11, 16, '3131ef17569c32fbe6a891a1f415370bc1d00bb4');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Dirk', 27057, 209, 9, 4, 17, 9, 1, 3, 7, 14, '1e096949bde2835580639613303b896a6e597f12');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Haskell', 43658, 1219, 6, 12, 13, 14, 14, 0, 6, 1, '8be984a373b48ffbc7f6955a35ef702640e2e64a');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Basil', 30578, 45, 3, 17, 9, 5, 6, 8, 19, 17, '148bdcf86501212af727bf1c3ab2a38fdd3406f1');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Skip', 22896, 2124, 13, 4, 19, 1, 16, 0, 11, 13, '25fd7b636432111f934a226d55016d11e7c7990d');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Sergeant', 22432, 242, 17, 3, 17, 16, 9, 1, 1, 13, '7256b3f1f3bbcac7716782df3f942349de515678');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Jeanne', 48861, 1055, 12, 16, 10, 18, 7, 5, 5, 19, '9722fd3ceef4cc7609b6b787045bdf37fa594c3f');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Concordia', 18516, 2178, 11, 2, 8, 17, 1, 9, 2, 14, 'c6ecfe46381c799c1abc546dfcff4204bc49dcf4');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Lidia', 13840, 49, 2, 14, 1, 13, 1, 5, 3, 9, 'f0f4235896042c94fc9b9aa75283efdcdb1f81c8');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Aloysius', 15597, 132, 5, 12, 15, 19, 3, 3, 8, 13, '6b9616b3e6c2c1e8a674ac7af47a9efead5bf844');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Toby', 40326, 2294, 6, 11, 9, 18, 4, 6, 18, 15, '186dc47feabbcb9febd6017c7835824f11a55952');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Oliy', 17110, 1470, 10, 9, 8, 17, 12, 8, 12, 4, 'f5f9c68a6e53aa7e47a88f2bb8ea4d425c53d544');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Teddy', 20056, 1585, 6, 11, 16, 10, 14, 2, 19, 14, 'b8863676cd0883c9ae311f29ca310847e8341067');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Pippo', 24054, 1429, 13, 15, 2, 2, 18, 5, 5, 14, 'a78caba2cb9e10e22bf9a49f147e0e3a951e59a2');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Doralin', 10691, 1093, 17, 16, 16, 14, 12, 2, 3, 16, 'cb852c212d4ae6da631bda131e001dbfd63c76ce');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Biddie', 45819, 1926, 17, 18, 19, 1, 13, 2, 10, 19, '491b42d40e06a1e7c61b4d7e503946936fc8a42a');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Clementius', 34385, 1381, 13, 8, 2, 8, 18, 7, 8, 10, '100df7bfa4e53b5411bd9db67100481ebb43573e');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Caleb', 11109, 453, 4, 1, 3, 2, 10, 9, 1, 16, '5f5b24852fd73127285d09569b1a9181b1540980');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Valentin', 14789, 26, 8, 1, 17, 3, 14, 8, 1, 5, '80cd5999c675d31bec3a19a63c8a10aa6e70261b');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Abelard', 24849, 1347, 9, 8, 3, 12, 8, 0, 12, 1, '6e7667441334dbc7beee5da21620a8641a67494a');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Sammy', 38745, 1172, 3, 7, 10, 7, 18, 4, 3, 9, 'a870d49a4969d626a3754670f5db102dfce8cc31');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Randi', 27980, 301, 2, 11, 4, 7, 13, 2, 10, 3, '5082a81318c7cb8afa8030bf052c685ca3dad09c');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Willard', 38738, 692, 8, 2, 1, 12, 17, 9, 18, 16, '50fa8348005023b4ed71cc77cb872c353bd98c3c');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Marjy', 44568, 1329, 16, 12, 8, 9, 17, 4, 3, 9, 'adf05e0166e14f2469353a375b07d20e0bf4ae79');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Adham', 45035, 675, 7, 2, 6, 4, 3, 0, 7, 16, '8085c19760370fb09e911c8afc91608d27986a38');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Ardith', 16224, 626, 19, 3, 7, 10, 14, 5, 3, 3, 'f584ae8b924f9f3ae362c592e998463227ab24ef');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Guinna', 28338, 1694, 5, 7, 5, 10, 1, 7, 3, 19, 'ceba69e13db00ff621b0061e0d22106a8437371b');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Hilda', 27553, 2247, 8, 16, 6, 2, 14, 8, 13, 5, '8065da5c39e70542e4be94adb0b64f39b89f4b6c');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Marlyn', 25471, 1679, 18, 1, 13, 18, 15, 5, 13, 11, '62712f9bc8c5deec36137bd9b150682e3333967d');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Edita', 17503, 1731, 2, 14, 5, 4, 2, 4, 14, 8, '4e328650fd7fcfa23d3beb42b2319d16f64a142a');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Othelia', 13005, 2071, 1, 12, 3, 9, 13, 6, 6, 4, '3a3f2218ecdf81c236911c3a439d48c74f5d4253');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Arlyn', 47569, 1208, 12, 17, 13, 11, 12, 10, 5, 4, '14e0f1edb538364787b364648e72ff9da08975d1');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Madlin', 40085, 1069, 3, 13, 4, 18, 9, 1, 15, 6, '2f6ad060bd74a0a7fb663c8cad1480d72c0cb99d');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Toddie', 10762, 264, 14, 15, 8, 6, 7, 3, 6, 2, '5b0c93f252ef869ec3b3c8f10b3487a0631cb377');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Joceline', 20632, 2233, 9, 18, 17, 17, 18, 1, 19, 18, '83be00e37c5d934871b4ab90d060c118d85f13ce');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Lauren', 29587, 1349, 13, 18, 11, 4, 5, 7, 1, 4, '550f85afc9fd965622f629d76a0f5f5af341064f');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Jacinthe', 39306, 1268, 15, 10, 1, 6, 6, 1, 10, 15, 'd79a5ac0c8a5fcb4c8b225c94a2b3769752da854');
insert into product_detail (name, price, quantity, id_pattern, id_color, id_origin, id_brand, id_material, id_size, id_styles, id_product, description) values ('Lothario', 42253, 247, 17, 7, 10, 6, 18, 8, 9, 10, '76076c0c276854baf4a5ae9d95799a2971f8531c');
