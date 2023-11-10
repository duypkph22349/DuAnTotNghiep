USE [master]
GO
/****** Object:  Database [DUANTN1]    Script Date: 11/10/2023 10:43:09 AM ******/
CREATE DATABASE [DUANTN1]
 go
USE [DUANTN1]
GO
/****** Object:  Table [dbo].[account]    Script Date: 11/10/2023 10:43:09 AM ******/

GO
CREATE TABLE [dbo].[account](
	[id] [uniqueidentifier] NOT NULL,
	[email] [nvarchar](100) NULL,
	[status] [int] NULL,
	[id_customer] [uniqueidentifier] NOT NULL,
	[created_at] [datetime] NULL,
	[updated_at] [datetime] NULL,
	[deleted] [bit] NULL,
	[password] [nvarchar](200) NULL,
	[image] [nvarchar](500) NULL,
 CONSTRAINT [PK_account] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[bill]    Script Date: 11/10/2023 10:43:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[bill](
	[id] [bigint] IDENTITY(0,1) NOT NULL,
	[id_customer] [uniqueidentifier] NOT NULL,
	[id_employee] [uniqueidentifier] NOT NULL,
	[id_pay] [bigint] NOT NULL,
	[code] [nvarchar](50) NULL,
	[confirmation_date] [datetime] NULL,
	[delivery_date] [datetime] NULL,
	[received_date] [datetime] NULL,
	[completion_date] [datetime] NULL,
	[customer_name] [nvarchar](50) NULL,
	[phone] [nvarchar](10) NULL,
	[address] [nvarchar](50) NULL,
	[money_ship] [float] NULL,
	[total_money] [decimal](20, 3) NULL,
	[reduction_amount] [decimal](20, 3) NULL,
	[deposit] [decimal](20, 3) NULL,
	[note] [nvarchar](500) NULL,
	[status] [int] NULL,
	[created_at] [datetime] NULL,
	[updated_at] [datetime] NULL,
	[deleted] [bit] NULL,
	[order_type] [int] NULL,
 CONSTRAINT [PK_bill] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[bill_detail]    Script Date: 11/10/2023 10:43:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[bill_detail](
	[id] [bigint] IDENTITY(0,1) NOT NULL,
	[id_product_detail] [bigint] NOT NULL,
	[id_bill] [bigint] NOT NULL,
	[total_money] [decimal](20, 3) NULL,
	[quantity] [int] NULL,
	[status] [int] NULL,
	[created_at] [datetime] NULL,
	[updated_at] [datetime] NULL,
	[deleted] [bit] NULL,
 CONSTRAINT [PK_bill_detail] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[brand]    Script Date: 11/10/2023 10:43:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[brand](
	[id] [bigint] IDENTITY(0,1) NOT NULL,
	[code] [nvarchar](25) NULL,
	[name] [nvarchar](500) NULL,
	[created_at] [datetime] NULL,
	[updated_at] [datetime] NULL,
	[status] [int] NULL,
	[deleted] [bit] NULL,
 CONSTRAINT [PK_trademark] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[cart]    Script Date: 11/10/2023 10:43:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[cart](
	[id] [bigint] IDENTITY(0,1) NOT NULL,
	[code] [nvarchar](25) NULL,
	[id_customer] [uniqueidentifier] NOT NULL,
	[start_time] [datetime] NULL,
	[quantity] [int] NULL,
	[total_money] [decimal](20, 3) NULL,
	[status] [int] NULL,
	[created_at] [datetime] NULL,
	[updated_at] [datetime] NULL,
	[deleted] [bit] NULL,
 CONSTRAINT [PK_cart] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[carts_detail]    Script Date: 11/10/2023 10:43:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[carts_detail](
	[id] [bigint] IDENTITY(0,1) NOT NULL,
	[quantity] [int] NULL,
	[id_cart] [bigint] NOT NULL,
	[id_product_detail] [bigint] NOT NULL,
	[status] [int] NULL,
	[created_at] [datetime] NULL,
	[updated_at] [datetime] NULL,
	[deleted] [bit] NULL,
 CONSTRAINT [PK_carts_detail] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[color]    Script Date: 11/10/2023 10:43:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[color](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[code] [nvarchar](25) NULL,
	[name] [nvarchar](500) NULL,
	[created_at] [datetime] NULL,
	[update_at] [datetime] NULL,
	[status] [int] NULL,
	[deleted] [bit] NULL,
 CONSTRAINT [PK_color] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[customer]    Script Date: 11/10/2023 10:43:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[customer](
	[id] [uniqueidentifier] NOT NULL,
	[code] [nvarchar](25) NULL,
	[name] [nvarchar](50) NULL,
	[birth_date] [datetime] NULL,
	[gender] [bit] NULL,
	[phone] [nvarchar](10) NULL,
	[address] [nvarchar](50) NULL,
	[status] [int] NULL,
	[created_at] [datetime] NULL,
	[update_at] [datetime] NULL,
	[deleted] [bit] NULL,
	[wardcode] [nvarchar](20) NULL,
	[districtcode] [int] NULL,
	[fulladdress] [nvarchar](100) NULL,
 CONSTRAINT [PK_customer] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[employee]    Script Date: 11/10/2023 10:43:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[employee](
	[id] [uniqueidentifier] NOT NULL,
	[id_roles] [bigint] NOT NULL,
	[code] [nvarchar](25) NULL,
	[name] [nvarchar](50) NULL,
	[gender] [bit] NULL,
	[birth_date] [datetime] NULL,
	[address] [nvarchar](500) NULL,
	[phone] [nvarchar](10) NULL,
	[email] [nchar](25) NULL,
	[status] [int] NULL,
	[created_at] [datetime] NULL,
	[update_at] [datetime] NULL,
	[deleted] [bit] NULL,
	[cccd] [nvarchar](50) NULL,
	[image] [nvarchar](max) NULL,
	[password] [nvarchar](200) NULL,
	[wardcode] [nvarchar](20) NULL,
	[districtcode] [int] NULL,
	[fulladdress] [nvarchar](100) NULL,
 CONSTRAINT [PK_staff] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[evaluate]    Script Date: 11/10/2023 10:43:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[evaluate](
	[id] [bigint] IDENTITY(0,1) NOT NULL,
	[id_product_detail] [bigint] NOT NULL,
	[id_customer] [uniqueidentifier] NOT NULL,
	[description] [nvarchar](500) NULL,
	[start_time] [datetime] NULL,
	[status] [int] NULL,
	[created_at] [datetime] NULL,
	[update_at] [datetime] NULL,
	[deleted] [bit] NULL,
 CONSTRAINT [PK_Table1] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[image]    Script Date: 11/10/2023 10:43:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[image](
	[id] [bigint] IDENTITY(0,1) NOT NULL,
	[id_product_detail] [bigint] NOT NULL,
	[image] [nvarchar](500) NULL,
	[discription] [nvarchar](500) NULL,
	[status] [int] NULL,
	[created_at] [datetime] NULL,
	[update_at] [datetime] NULL,
	[deleted] [bit] NULL,
 CONSTRAINT [PK_image] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[material]    Script Date: 11/10/2023 10:43:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[material](
	[id] [bigint] IDENTITY(0,1) NOT NULL,
	[code] [nchar](25) NOT NULL,
	[name] [nvarchar](500) NULL,
	[created_at] [datetime] NULL,
	[update_at] [datetime] NULL,
	[status] [int] NULL,
	[deleted] [bit] NULL,
 CONSTRAINT [PK_material] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[origin]    Script Date: 11/10/2023 10:43:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[origin](
	[id] [bigint] IDENTITY(0,1) NOT NULL,
	[code] [nvarchar](25) NULL,
	[name] [nvarchar](500) NULL,
	[created_at] [datetime] NULL,
	[update_at] [datetime] NULL,
	[status] [int] NULL,
	[deleted] [bit] NULL,
 CONSTRAINT [PK_origin] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[pattern]    Script Date: 11/10/2023 10:43:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[pattern](
	[id] [bigint] IDENTITY(0,1) NOT NULL,
	[code] [nvarchar](25) NULL,
	[name] [nvarchar](500) NULL,
	[created_at] [datetime] NULL,
	[update_at] [datetime] NULL,
	[status] [int] NULL,
	[deleted] [bit] NULL,
 CONSTRAINT [PK_Pattern_Type] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[pay]    Script Date: 11/10/2023 10:43:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[pay](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[payment_method] [nvarchar](50) NULL,
	[status] [int] NULL,
	[created_at] [datetime] NULL,
	[update_at] [datetime] NULL,
	[deleted] [bit] NULL,
 CONSTRAINT [PK_pay] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[product]    Script Date: 11/10/2023 10:43:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product](
	[id] [bigint] IDENTITY(0,1) NOT NULL,
	[code] [nvarchar](25) NULL,
	[name] [nvarchar](50) NULL,
	[status] [int] NULL,
	[created_at] [datetime] NULL,
	[updated_at] [datetime] NULL,
	[deleted] [bit] NULL,
 CONSTRAINT [PK_product] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[product_detail]    Script Date: 11/10/2023 10:43:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product_detail](
	[id] [bigint] IDENTITY(0,1) NOT NULL,
	[code] [nvarchar](100) NULL,
	[name] [nvarchar](100) NULL,
	[price] [decimal](20, 3) NULL,
	[quantity] [int] NULL,
	[description] [nvarchar](500) NULL,
	[id_pattern] [bigint] NULL,
	[id_color] [bigint] NULL,
	[id_origin] [bigint] NULL,
	[id_brand] [bigint] NULL,
	[id_material] [bigint] NULL,
	[id_size] [bigint] NULL,
	[id_styles] [bigint] NULL,
	[status] [int] NULL,
	[created_at] [datetime] NULL,
	[update_at] [datetime] NULL,
	[deleted] [bit] NULL,
	[id_product] [bigint] NULL,
 CONSTRAINT [PK_product_detail] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[roles]    Script Date: 11/10/2023 10:43:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[roles](
	[id] [bigint] IDENTITY(0,1) NOT NULL,
	[name] [nvarchar](50) NULL,
	[status] [int] NULL,
	[created_at] [datetime] NULL,
	[update_at] [datetime] NULL,
	[deleted] [bit] NULL,
	[role] [nvarchar](50) NULL,
 CONSTRAINT [PK_roles] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[size]    Script Date: 11/10/2023 10:43:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[size](
	[id] [bigint] IDENTITY(0,1) NOT NULL,
	[code] [nvarchar](25) NULL,
	[name] [nvarchar](500) NULL,
	[created_at] [datetime] NULL,
	[update_at] [datetime] NULL,
	[status] [int] NULL,
	[deleted] [bit] NULL,
 CONSTRAINT [PK_side] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[styles]    Script Date: 11/10/2023 10:43:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[styles](
	[id] [bigint] IDENTITY(0,1) NOT NULL,
	[code] [nvarchar](25) NULL,
	[name] [nvarchar](500) NULL,
	[created_at] [datetime] NULL,
	[update_at] [datetime] NULL,
	[status] [int] NULL,
	[deleted] [bit] NULL,
 CONSTRAINT [PK_styles] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[voucher]    Script Date: 11/10/2023 10:43:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[voucher](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[code] [nvarchar](25) NULL,
	[name] [nvarchar](50) NULL,
	[start_time] [datetime] NULL,
	[end_time] [datetime] NULL,
	[quantily] [int] NULL,
	[discount] [float] NULL,
	[status] [int] NULL,
	[created_at] [datetime] NULL,
	[update_at] [datetime] NULL,
	[types] [bit] NULL,
	[deleted] [bit] NULL,
	[max_discount] [float] NULL,
	[min_order] [float] NULL,
 CONSTRAINT [PK_voucher] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[voucher_detail]    Script Date: 11/10/2023 10:43:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[voucher_detail](
	[id] [bigint] IDENTITY(0,1) NOT NULL,
	[id_bill] [bigint] NOT NULL,
	[id_voucher] [bigint] NOT NULL,
	[money_before_reduction] [decimal](20, 3) NULL,
	[money_after_reduction] [decimal](20, 3) NULL,
	[money_reduction] [decimal](20, 3) NULL,
	[status] [int] NULL,
	[created_at] [datetime] NULL,
	[update_at] [datetime] NULL,
	[deleted] [bit] NULL,
 CONSTRAINT [PK_voucher_detail] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[account] ([id], [email], [status], [id_customer], [created_at], [updated_at], [deleted], [password], [image]) VALUES (N'2c2282a8-2c64-4a62-a571-0a0917957c75', N'quynhanh@gmail.com', 1, N'ae9884e4-0e44-47c2-b356-abb655117c71', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, N'0000', NULL)
GO
INSERT [dbo].[account] ([id], [email], [status], [id_customer], [created_at], [updated_at], [deleted], [password], [image]) VALUES (N'72959072-ccc6-4f72-b083-24567aec4ebe', N'ha@gmail.com', 1, N'7e073e67-0c6b-4010-8ae0-c9688218857e', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, N'0000', NULL)
GO
INSERT [dbo].[account] ([id], [email], [status], [id_customer], [created_at], [updated_at], [deleted], [password], [image]) VALUES (N'7285de1f-65b9-44e7-8142-9069ae4eb367', N'that@gmail.com', 1, N'03bef975-baa5-43ad-ab1f-e4826eb049d6', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, N'0000', NULL)
GO
INSERT [dbo].[account] ([id], [email], [status], [id_customer], [created_at], [updated_at], [deleted], [password], [image]) VALUES (N'81158020-247e-4c91-878b-9e2bb295ff63', N'thinh@gmail.com', 1, N'8d422546-4820-4ccf-9d30-575fe485d3ac', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, N'0000', NULL)
GO
INSERT [dbo].[account] ([id], [email], [status], [id_customer], [created_at], [updated_at], [deleted], [password], [image]) VALUES (N'041d0892-ff8b-4ec1-bc50-c0bcdfb3f429', N'huong@gmail.com', 1, N'950fcc94-e428-4078-8d45-0e3887c384d9', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, N'0000', NULL)
GO
SET IDENTITY_INSERT [dbo].[bill] ON 
GO
INSERT [dbo].[bill] ([id], [id_customer], [id_employee], [id_pay], [code], [confirmation_date], [delivery_date], [received_date], [completion_date], [customer_name], [phone], [address], [money_ship], [total_money], [reduction_amount], [deposit], [note], [status], [created_at], [updated_at], [deleted], [order_type]) VALUES (1, N'64ac93b6-a093-4f7a-84ff-050e5773385a', N'33187a7d-d77d-4000-9483-03c4c927065e', 1, N'B01', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-09T00:00:00.000' AS DateTime), CAST(N'2023-11-10T00:00:00.000' AS DateTime), CAST(N'2023-11-10T00:00:00.000' AS DateTime), N'Hương', N'0973554745', N'An Thai', 30000, CAST(230000.000 AS Decimal(20, 3)), CAST(0.000 AS Decimal(20, 3)), CAST(230000.000 AS Decimal(20, 3)), N'giao lúc 2h chiều', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 1)
GO
INSERT [dbo].[bill] ([id], [id_customer], [id_employee], [id_pay], [code], [confirmation_date], [delivery_date], [received_date], [completion_date], [customer_name], [phone], [address], [money_ship], [total_money], [reduction_amount], [deposit], [note], [status], [created_at], [updated_at], [deleted], [order_type]) VALUES (2, N'99903190-37a9-48fd-b8e0-1db1779736fe', N'5de1e4f2-a94a-4201-9085-06acdff42ca8', 2, N'B02', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-09T00:00:00.000' AS DateTime), CAST(N'2023-11-10T00:00:00.000' AS DateTime), CAST(N'2023-11-10T00:00:00.000' AS DateTime), N'Thịnh', N'0335535229', N'Hòa Bình', 30000, CAST(430000.000 AS Decimal(20, 3)), CAST(0.000 AS Decimal(20, 3)), CAST(430000.000 AS Decimal(20, 3)), N'giao hàng ở công ty', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 1)
GO
INSERT [dbo].[bill] ([id], [id_customer], [id_employee], [id_pay], [code], [confirmation_date], [delivery_date], [received_date], [completion_date], [customer_name], [phone], [address], [money_ship], [total_money], [reduction_amount], [deposit], [note], [status], [created_at], [updated_at], [deleted], [order_type]) VALUES (3, N'41f91c39-1036-40e8-af4c-4b0605ca45bd', N'c65ac0ad-e17a-42b8-aa94-07465f17ace7', 1, N'B03', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-09T00:00:00.000' AS DateTime), CAST(N'2023-11-10T00:00:00.000' AS DateTime), CAST(N'2023-11-10T00:00:00.000' AS DateTime), N'Quỳnh Anh', N'0918901234', N'Địch Vĩ', 30000, CAST(300000.000 AS Decimal(20, 3)), CAST(0.000 AS Decimal(20, 3)), CAST(300000.000 AS Decimal(20, 3)), N'giao lúc 5h chiều', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 1)
GO
INSERT [dbo].[bill] ([id], [id_customer], [id_employee], [id_pay], [code], [confirmation_date], [delivery_date], [received_date], [completion_date], [customer_name], [phone], [address], [money_ship], [total_money], [reduction_amount], [deposit], [note], [status], [created_at], [updated_at], [deleted], [order_type]) VALUES (4, N'3a6863ba-ce82-4eee-a19b-6653e350d26e', N'460b2017-e7f4-482a-9b33-14b10a0a88cc', 2, N'B04', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-09T00:00:00.000' AS DateTime), CAST(N'2023-11-10T00:00:00.000' AS DateTime), CAST(N'2023-11-10T00:00:00.000' AS DateTime), N'Hà', N'0921345678', N'Địch Vĩ', 30000, CAST(230000.000 AS Decimal(20, 3)), CAST(0.000 AS Decimal(20, 3)), CAST(230000.000 AS Decimal(20, 3)), N'giao hàng ở công ty', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 1)
GO
INSERT [dbo].[bill] ([id], [id_customer], [id_employee], [id_pay], [code], [confirmation_date], [delivery_date], [received_date], [completion_date], [customer_name], [phone], [address], [money_ship], [total_money], [reduction_amount], [deposit], [note], [status], [created_at], [updated_at], [deleted], [order_type]) VALUES (5, N'3026a5bd-e215-4edb-8a32-db71c1a7d750', N'124376e7-524e-4c72-b136-23a8c4008bef', 1, N'B05', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-09T00:00:00.000' AS DateTime), CAST(N'2023-11-10T00:00:00.000' AS DateTime), CAST(N'2023-11-10T00:00:00.000' AS DateTime), N'Thật', N'0955678901', N'Đại Đồng', 30000, CAST(830000.000 AS Decimal(20, 3)), CAST(0.000 AS Decimal(20, 3)), CAST(830000.000 AS Decimal(20, 3)), N'giao hàng ở công ty', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 1)
GO
INSERT [dbo].[bill] ([id], [id_customer], [id_employee], [id_pay], [code], [confirmation_date], [delivery_date], [received_date], [completion_date], [customer_name], [phone], [address], [money_ship], [total_money], [reduction_amount], [deposit], [note], [status], [created_at], [updated_at], [deleted], [order_type]) VALUES (6, N'79a3a3c5-4e10-46a3-855a-080aee7fb16d', N'33187a7d-d77d-4000-9483-03c4c927065e', 2, N'B06', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-09T00:00:00.000' AS DateTime), CAST(N'2023-11-10T00:00:00.000' AS DateTime), CAST(N'2023-11-10T00:00:00.000' AS DateTime), N'Thanh', N'0987654321', N'An Thái', 0, CAST(400000.000 AS Decimal(20, 3)), CAST(30000.000 AS Decimal(20, 3)), CAST(400000.000 AS Decimal(20, 3)), N'giao lúc 2h chiều', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 1)
GO
INSERT [dbo].[bill] ([id], [id_customer], [id_employee], [id_pay], [code], [confirmation_date], [delivery_date], [received_date], [completion_date], [customer_name], [phone], [address], [money_ship], [total_money], [reduction_amount], [deposit], [note], [status], [created_at], [updated_at], [deleted], [order_type]) VALUES (7, N'e1211807-d831-4b42-b62f-09ff2f9397a9', N'5de1e4f2-a94a-4201-9085-06acdff42ca8', 1, N'B07', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-09T00:00:00.000' AS DateTime), CAST(N'2023-11-10T00:00:00.000' AS DateTime), CAST(N'2023-11-10T00:00:00.000' AS DateTime), N'Mai', N'0975123456', N'An Thái', 0, CAST(200000.000 AS Decimal(20, 3)), CAST(30000.000 AS Decimal(20, 3)), CAST(200000.000 AS Decimal(20, 3)), N'giao lúc 5h chiều', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 0)
GO
INSERT [dbo].[bill] ([id], [id_customer], [id_employee], [id_pay], [code], [confirmation_date], [delivery_date], [received_date], [completion_date], [customer_name], [phone], [address], [money_ship], [total_money], [reduction_amount], [deposit], [note], [status], [created_at], [updated_at], [deleted], [order_type]) VALUES (8, N'950fcc94-e428-4078-8d45-0e3887c384d9', N'c65ac0ad-e17a-42b8-aa94-07465f17ace7', 2, N'B08', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-09T00:00:00.000' AS DateTime), CAST(N'2023-11-10T00:00:00.000' AS DateTime), CAST(N'2023-11-10T00:00:00.000' AS DateTime), N'Hoài', N'0934567890', N'An Thái', 30000, CAST(360000.000 AS Decimal(20, 3)), CAST(0.000 AS Decimal(20, 3)), CAST(360000.000 AS Decimal(20, 3)), N'giao nhanh giúp mình nhé', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 0)
GO
INSERT [dbo].[bill] ([id], [id_customer], [id_employee], [id_pay], [code], [confirmation_date], [delivery_date], [received_date], [completion_date], [customer_name], [phone], [address], [money_ship], [total_money], [reduction_amount], [deposit], [note], [status], [created_at], [updated_at], [deleted], [order_type]) VALUES (9, N'5e95507c-932f-4a64-90f0-3308f66e2edc', N'460b2017-e7f4-482a-9b33-14b10a0a88cc', 1, N'B09', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-09T00:00:00.000' AS DateTime), CAST(N'2023-11-10T00:00:00.000' AS DateTime), CAST(N'2023-11-10T00:00:00.000' AS DateTime), N'Nam', N'0934567890', N'Đại Đồng', 0, CAST(450000.000 AS Decimal(20, 3)), CAST(30000.000 AS Decimal(20, 3)), CAST(450000.000 AS Decimal(20, 3)), N'cần gấp', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 0)
GO
INSERT [dbo].[bill] ([id], [id_customer], [id_employee], [id_pay], [code], [confirmation_date], [delivery_date], [received_date], [completion_date], [customer_name], [phone], [address], [money_ship], [total_money], [reduction_amount], [deposit], [note], [status], [created_at], [updated_at], [deleted], [order_type]) VALUES (10, N'ca69225b-b192-41f9-a056-384f94970ea0', N'124376e7-524e-4c72-b136-23a8c4008bef', 2, N'B10', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-09T00:00:00.000' AS DateTime), CAST(N'2023-11-10T00:00:00.000' AS DateTime), CAST(N'2023-11-10T00:00:00.000' AS DateTime), N'Khánh', N'0921345678', N'Địch Vĩ', 30000, CAST(500000.000 AS Decimal(20, 3)), CAST(0.000 AS Decimal(20, 3)), CAST(500000.000 AS Decimal(20, 3)), N'giao hàng ở công ty', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 0)
GO
INSERT [dbo].[bill] ([id], [id_customer], [id_employee], [id_pay], [code], [confirmation_date], [delivery_date], [received_date], [completion_date], [customer_name], [phone], [address], [money_ship], [total_money], [reduction_amount], [deposit], [note], [status], [created_at], [updated_at], [deleted], [order_type]) VALUES (13, N'40162844-7d69-4736-b520-3a8d546bd176', N'c70a9a5a-b07a-4964-8839-3b41372e22e6', 1, N'B11', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-09T00:00:00.000' AS DateTime), CAST(N'2023-11-10T00:00:00.000' AS DateTime), CAST(N'2023-11-10T00:00:00.000' AS DateTime), N'Hùng', N'0955678901', N'Địch Vĩ', 30000, CAST(700000.000 AS Decimal(20, 3)), CAST(0.000 AS Decimal(20, 3)), CAST(700000.000 AS Decimal(20, 3)), N'giao lúc 5h chiều', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 0)
GO
INSERT [dbo].[bill] ([id], [id_customer], [id_employee], [id_pay], [code], [confirmation_date], [delivery_date], [received_date], [completion_date], [customer_name], [phone], [address], [money_ship], [total_money], [reduction_amount], [deposit], [note], [status], [created_at], [updated_at], [deleted], [order_type]) VALUES (14, N'c6626b2d-d721-4285-bf06-51930e7d2ac3', N'750bc699-8ce0-42a0-a5cc-4ecb28a86d97', 1, N'B12', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-09T00:00:00.000' AS DateTime), CAST(N'2023-11-10T00:00:00.000' AS DateTime), CAST(N'2023-11-10T00:00:00.000' AS DateTime), N'Hằng', N'0912345678', N'Hòa Bình', 0, CAST(340000.000 AS Decimal(20, 3)), CAST(30000.000 AS Decimal(20, 3)), CAST(340000.000 AS Decimal(20, 3)), N'Khách dễ tính', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 1)
GO
INSERT [dbo].[bill] ([id], [id_customer], [id_employee], [id_pay], [code], [confirmation_date], [delivery_date], [received_date], [completion_date], [customer_name], [phone], [address], [money_ship], [total_money], [reduction_amount], [deposit], [note], [status], [created_at], [updated_at], [deleted], [order_type]) VALUES (15, N'634cb5c8-f1a0-44d0-b8a9-5298e04255d8', N'32b8bc44-e1a4-4547-9c9f-5ebf9bd74bcf', 1, N'B13', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-09T00:00:00.000' AS DateTime), CAST(N'2023-11-10T00:00:00.000' AS DateTime), CAST(N'2023-11-10T00:00:00.000' AS DateTime), N'Trang', N'0975123456', N'Hòa Bình', 0, CAST(830000.000 AS Decimal(20, 3)), CAST(30000.000 AS Decimal(20, 3)), CAST(830000.000 AS Decimal(20, 3)), N'Khách khó tính', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 0)
GO
INSERT [dbo].[bill] ([id], [id_customer], [id_employee], [id_pay], [code], [confirmation_date], [delivery_date], [received_date], [completion_date], [customer_name], [phone], [address], [money_ship], [total_money], [reduction_amount], [deposit], [note], [status], [created_at], [updated_at], [deleted], [order_type]) VALUES (17, N'8d422546-4820-4ccf-9d30-575fe485d3ac', N'5f2d6629-9a69-41dd-ad27-6cc5bc8693ee', 2, N'B14', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-09T00:00:00.000' AS DateTime), CAST(N'2023-11-10T00:00:00.000' AS DateTime), CAST(N'2023-11-10T00:00:00.000' AS DateTime), N'Hiếu', N'0901234567', N'Hòa Bình', 30000, CAST(430000.000 AS Decimal(20, 3)), CAST(0.000 AS Decimal(20, 3)), CAST(430000.000 AS Decimal(20, 3)), N'giao lúc 3h chiều', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 0)
GO
INSERT [dbo].[bill] ([id], [id_customer], [id_employee], [id_pay], [code], [confirmation_date], [delivery_date], [received_date], [completion_date], [customer_name], [phone], [address], [money_ship], [total_money], [reduction_amount], [deposit], [note], [status], [created_at], [updated_at], [deleted], [order_type]) VALUES (18, N'3a6863ba-ce82-4eee-a19b-6653e350d26e', N'32b8bc44-e1a4-4547-9c9f-5ebf9bd74bcf', 1, N'B15', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-09T00:00:00.000' AS DateTime), CAST(N'2023-11-10T00:00:00.000' AS DateTime), CAST(N'2023-11-10T00:00:00.000' AS DateTime), N'Hà', N'0921345678', N'Địch Vĩ', 30000, CAST(230000.000 AS Decimal(20, 3)), CAST(0.000 AS Decimal(20, 3)), CAST(230000.000 AS Decimal(20, 3)), N'cần gấp', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 1)
GO
INSERT [dbo].[bill] ([id], [id_customer], [id_employee], [id_pay], [code], [confirmation_date], [delivery_date], [received_date], [completion_date], [customer_name], [phone], [address], [money_ship], [total_money], [reduction_amount], [deposit], [note], [status], [created_at], [updated_at], [deleted], [order_type]) VALUES (19, N'77d0ef38-3949-4c93-b096-8a9b09e6ab5f', N'750bc699-8ce0-42a0-a5cc-4ecb28a86d97', 2, N'B16', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-09T00:00:00.000' AS DateTime), CAST(N'2023-11-10T00:00:00.000' AS DateTime), CAST(N'2023-11-10T00:00:00.000' AS DateTime), N'Cường', N'0987654321', N'Địch Vĩ', 30000, CAST(480000.000 AS Decimal(20, 3)), CAST(0.000 AS Decimal(20, 3)), CAST(480000.000 AS Decimal(20, 3)), N'giao lúc 5h chiều', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 1)
GO
INSERT [dbo].[bill] ([id], [id_customer], [id_employee], [id_pay], [code], [confirmation_date], [delivery_date], [received_date], [completion_date], [customer_name], [phone], [address], [money_ship], [total_money], [reduction_amount], [deposit], [note], [status], [created_at], [updated_at], [deleted], [order_type]) VALUES (20, N'be8cc949-a72f-42a0-898e-99618bcd4650', N'c70a9a5a-b07a-4964-8839-3b41372e22e6', 1, N'B17', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-09T00:00:00.000' AS DateTime), CAST(N'2023-11-10T00:00:00.000' AS DateTime), CAST(N'2023-11-10T00:00:00.000' AS DateTime), N'Lan', N'0955678901', N'Địch Vĩ', 30000, CAST(230000.000 AS Decimal(20, 3)), CAST(0.000 AS Decimal(20, 3)), CAST(230000.000 AS Decimal(20, 3)), N'giao hàng ở công ty', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 1)
GO
INSERT [dbo].[bill] ([id], [id_customer], [id_employee], [id_pay], [code], [confirmation_date], [delivery_date], [received_date], [completion_date], [customer_name], [phone], [address], [money_ship], [total_money], [reduction_amount], [deposit], [note], [status], [created_at], [updated_at], [deleted], [order_type]) VALUES (21, N'ae9884e4-0e44-47c2-b356-abb655117c71', N'28e14c18-3485-43a5-ada3-259867821184', 1, N'B18', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-09T00:00:00.000' AS DateTime), CAST(N'2023-11-10T00:00:00.000' AS DateTime), CAST(N'2023-11-10T00:00:00.000' AS DateTime), N'Ngọc', N'0921345678', N'Địch Vĩ', 30000, CAST(430000.000 AS Decimal(20, 3)), CAST(0.000 AS Decimal(20, 3)), CAST(430000.000 AS Decimal(20, 3)), N'giao hàng ở công ty', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 0)
GO
INSERT [dbo].[bill] ([id], [id_customer], [id_employee], [id_pay], [code], [confirmation_date], [delivery_date], [received_date], [completion_date], [customer_name], [phone], [address], [money_ship], [total_money], [reduction_amount], [deposit], [note], [status], [created_at], [updated_at], [deleted], [order_type]) VALUES (22, N'f81555a9-22a4-4679-a3d9-bb497179d059', N'124376e7-524e-4c72-b136-23a8c4008bef', 2, N'B19', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-09T00:00:00.000' AS DateTime), CAST(N'2023-11-10T00:00:00.000' AS DateTime), CAST(N'2023-11-10T00:00:00.000' AS DateTime), N'Chiến', N'0975123456', N'Đại Đồng', 30000, CAST(300000.000 AS Decimal(20, 3)), CAST(0.000 AS Decimal(20, 3)), CAST(30000.000 AS Decimal(20, 3)), N'giao nhanh giúp mình nhé', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 0)
GO
INSERT [dbo].[bill] ([id], [id_customer], [id_employee], [id_pay], [code], [confirmation_date], [delivery_date], [received_date], [completion_date], [customer_name], [phone], [address], [money_ship], [total_money], [reduction_amount], [deposit], [note], [status], [created_at], [updated_at], [deleted], [order_type]) VALUES (23, N'7e073e67-0c6b-4010-8ae0-c9688218857e', N'460b2017-e7f4-482a-9b33-14b10a0a88cc', 1, N'B20', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-09T00:00:00.000' AS DateTime), CAST(N'2023-11-10T00:00:00.000' AS DateTime), CAST(N'2023-11-10T00:00:00.000' AS DateTime), N'Bình', N'0973554745', N'Đại Đồng', 30000, CAST(830000.000 AS Decimal(20, 3)), CAST(0.000 AS Decimal(20, 3)), CAST(830000.000 AS Decimal(20, 3)), N'cần gấp', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), NULL, 0, 0)
GO
SET IDENTITY_INSERT [dbo].[bill] OFF
GO
SET IDENTITY_INSERT [dbo].[bill_detail] ON 
GO
INSERT [dbo].[bill_detail] ([id], [id_product_detail], [id_bill], [total_money], [quantity], [status], [created_at], [updated_at], [deleted]) VALUES (0, 0, 1, CAST(230000.000 AS Decimal(20, 3)), 1, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[bill_detail] ([id], [id_product_detail], [id_bill], [total_money], [quantity], [status], [created_at], [updated_at], [deleted]) VALUES (1, 1, 2, CAST(430000.000 AS Decimal(20, 3)), 3, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[bill_detail] ([id], [id_product_detail], [id_bill], [total_money], [quantity], [status], [created_at], [updated_at], [deleted]) VALUES (3, 13, 3, CAST(300000.000 AS Decimal(20, 3)), 2, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[bill_detail] ([id], [id_product_detail], [id_bill], [total_money], [quantity], [status], [created_at], [updated_at], [deleted]) VALUES (4, 1, 4, CAST(230000.000 AS Decimal(20, 3)), 1, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[bill_detail] ([id], [id_product_detail], [id_bill], [total_money], [quantity], [status], [created_at], [updated_at], [deleted]) VALUES (5, 18, 5, CAST(830000.000 AS Decimal(20, 3)), 6, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[bill_detail] ([id], [id_product_detail], [id_bill], [total_money], [quantity], [status], [created_at], [updated_at], [deleted]) VALUES (6, 2, 6, CAST(400000.000 AS Decimal(20, 3)), 2, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[bill_detail] ([id], [id_product_detail], [id_bill], [total_money], [quantity], [status], [created_at], [updated_at], [deleted]) VALUES (7, 1, 7, CAST(200000.000 AS Decimal(20, 3)), 1, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[bill_detail] ([id], [id_product_detail], [id_bill], [total_money], [quantity], [status], [created_at], [updated_at], [deleted]) VALUES (8, 0, 8, CAST(360000.000 AS Decimal(20, 3)), 3, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[bill_detail] ([id], [id_product_detail], [id_bill], [total_money], [quantity], [status], [created_at], [updated_at], [deleted]) VALUES (9, 22, 9, CAST(450000.000 AS Decimal(20, 3)), 3, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[bill_detail] ([id], [id_product_detail], [id_bill], [total_money], [quantity], [status], [created_at], [updated_at], [deleted]) VALUES (10, 1, 10, CAST(130000.000 AS Decimal(20, 3)), 1, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[bill_detail] ([id], [id_product_detail], [id_bill], [total_money], [quantity], [status], [created_at], [updated_at], [deleted]) VALUES (11, 13, 10, CAST(400000.000 AS Decimal(20, 3)), 2, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
SET IDENTITY_INSERT [dbo].[bill_detail] OFF
GO
SET IDENTITY_INSERT [dbo].[brand] ON 
GO
INSERT [dbo].[brand] ([id], [code], [name], [created_at], [updated_at], [status], [deleted]) VALUES (0, N'BR01', N'Burberry', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, 0)
GO
INSERT [dbo].[brand] ([id], [code], [name], [created_at], [updated_at], [status], [deleted]) VALUES (1, N'BR02', N'Jussy', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, 0)
GO
INSERT [dbo].[brand] ([id], [code], [name], [created_at], [updated_at], [status], [deleted]) VALUES (2, N'BR03', N'HUMI', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, 0)
GO
INSERT [dbo].[brand] ([id], [code], [name], [created_at], [updated_at], [status], [deleted]) VALUES (3, N'BR04', N'Gia Hân', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, 0)
GO
INSERT [dbo].[brand] ([id], [code], [name], [created_at], [updated_at], [status], [deleted]) VALUES (4, N'BR05', N'LAGU', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, 0)
GO
SET IDENTITY_INSERT [dbo].[brand] OFF
GO
SET IDENTITY_INSERT [dbo].[cart] ON 
GO
INSERT [dbo].[cart] ([id], [code], [id_customer], [start_time], [quantity], [total_money], [status], [created_at], [updated_at], [deleted]) VALUES (0, N'C01', N'64ac93b6-a093-4f7a-84ff-050e5773385a', CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, CAST(230000.000 AS Decimal(20, 3)), 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[cart] ([id], [code], [id_customer], [start_time], [quantity], [total_money], [status], [created_at], [updated_at], [deleted]) VALUES (1, N'C02', N'99903190-37a9-48fd-b8e0-1db1779736fe', CAST(N'2023-11-08T00:00:00.000' AS DateTime), 2, CAST(430000.000 AS Decimal(20, 3)), 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[cart] ([id], [code], [id_customer], [start_time], [quantity], [total_money], [status], [created_at], [updated_at], [deleted]) VALUES (2, N'C03', N'41f91c39-1036-40e8-af4c-4b0605ca45bd', CAST(N'2023-11-08T00:00:00.000' AS DateTime), 2, CAST(300000.000 AS Decimal(20, 3)), 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[cart] ([id], [code], [id_customer], [start_time], [quantity], [total_money], [status], [created_at], [updated_at], [deleted]) VALUES (3, N'C04', N'3a6863ba-ce82-4eee-a19b-6653e350d26e', CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, CAST(230000.000 AS Decimal(20, 3)), 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[cart] ([id], [code], [id_customer], [start_time], [quantity], [total_money], [status], [created_at], [updated_at], [deleted]) VALUES (4, N'C05', N'3026a5bd-e215-4edb-8a32-db71c1a7d750', CAST(N'2023-11-08T00:00:00.000' AS DateTime), 4, CAST(830000.000 AS Decimal(20, 3)), 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[cart] ([id], [code], [id_customer], [start_time], [quantity], [total_money], [status], [created_at], [updated_at], [deleted]) VALUES (5, N'C06', N'79a3a3c5-4e10-46a3-855a-080aee7fb16d', CAST(N'2023-11-08T00:00:00.000' AS DateTime), 2, CAST(400000.000 AS Decimal(20, 3)), 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[cart] ([id], [code], [id_customer], [start_time], [quantity], [total_money], [status], [created_at], [updated_at], [deleted]) VALUES (6, N'C07', N'e1211807-d831-4b42-b62f-09ff2f9397a9', CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, CAST(200000.000 AS Decimal(20, 3)), 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[cart] ([id], [code], [id_customer], [start_time], [quantity], [total_money], [status], [created_at], [updated_at], [deleted]) VALUES (7, N'C08', N'950fcc94-e428-4078-8d45-0e3887c384d9', CAST(N'2023-11-08T00:00:00.000' AS DateTime), 2, CAST(360000.000 AS Decimal(20, 3)), 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[cart] ([id], [code], [id_customer], [start_time], [quantity], [total_money], [status], [created_at], [updated_at], [deleted]) VALUES (8, N'C09', N'5e95507c-932f-4a64-90f0-3308f66e2edc', CAST(N'2023-11-08T00:00:00.000' AS DateTime), 3, CAST(450000.000 AS Decimal(20, 3)), 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[cart] ([id], [code], [id_customer], [start_time], [quantity], [total_money], [status], [created_at], [updated_at], [deleted]) VALUES (9, N'C10', N'ca69225b-b192-41f9-a056-384f94970ea0', CAST(N'2023-11-08T00:00:00.000' AS DateTime), 3, CAST(500000.000 AS Decimal(20, 3)), 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[cart] ([id], [code], [id_customer], [start_time], [quantity], [total_money], [status], [created_at], [updated_at], [deleted]) VALUES (10, N'C11', N'40162844-7d69-4736-b520-3a8d546bd176', CAST(N'2023-11-08T00:00:00.000' AS DateTime), 5, CAST(700000.000 AS Decimal(20, 3)), 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[cart] ([id], [code], [id_customer], [start_time], [quantity], [total_money], [status], [created_at], [updated_at], [deleted]) VALUES (11, N'C12', N'c6626b2d-d721-4285-bf06-51930e7d2ac3', CAST(N'2023-11-08T00:00:00.000' AS DateTime), 3, CAST(340000.000 AS Decimal(20, 3)), 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[cart] ([id], [code], [id_customer], [start_time], [quantity], [total_money], [status], [created_at], [updated_at], [deleted]) VALUES (12, N'C13', N'634cb5c8-f1a0-44d0-b8a9-5298e04255d8', CAST(N'2023-11-08T00:00:00.000' AS DateTime), 6, CAST(830000.000 AS Decimal(20, 3)), 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[cart] ([id], [code], [id_customer], [start_time], [quantity], [total_money], [status], [created_at], [updated_at], [deleted]) VALUES (13, N'C14', N'8d422546-4820-4ccf-9d30-575fe485d3ac', CAST(N'2023-11-08T00:00:00.000' AS DateTime), 3, CAST(430000.000 AS Decimal(20, 3)), 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[cart] ([id], [code], [id_customer], [start_time], [quantity], [total_money], [status], [created_at], [updated_at], [deleted]) VALUES (14, N'C15', N'3a6863ba-ce82-4eee-a19b-6653e350d26e', CAST(N'2023-11-08T00:00:00.000' AS DateTime), 2, CAST(230000.000 AS Decimal(20, 3)), 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[cart] ([id], [code], [id_customer], [start_time], [quantity], [total_money], [status], [created_at], [updated_at], [deleted]) VALUES (15, N'C16', N'77d0ef38-3949-4c93-b096-8a9b09e6ab5f', CAST(N'2023-11-08T00:00:00.000' AS DateTime), 3, CAST(480000.000 AS Decimal(20, 3)), 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[cart] ([id], [code], [id_customer], [start_time], [quantity], [total_money], [status], [created_at], [updated_at], [deleted]) VALUES (16, N'C17', N'be8cc949-a72f-42a0-898e-99618bcd4650', CAST(N'2023-11-08T00:00:00.000' AS DateTime), 2, CAST(230000.000 AS Decimal(20, 3)), 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[cart] ([id], [code], [id_customer], [start_time], [quantity], [total_money], [status], [created_at], [updated_at], [deleted]) VALUES (17, N'C18', N'ae9884e4-0e44-47c2-b356-abb655117c71', CAST(N'2023-11-08T00:00:00.000' AS DateTime), 3, CAST(430000.000 AS Decimal(20, 3)), 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[cart] ([id], [code], [id_customer], [start_time], [quantity], [total_money], [status], [created_at], [updated_at], [deleted]) VALUES (18, N'C19', N'f81555a9-22a4-4679-a3d9-bb497179d059', CAST(N'2023-11-08T00:00:00.000' AS DateTime), 2, CAST(300000.000 AS Decimal(20, 3)), 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[cart] ([id], [code], [id_customer], [start_time], [quantity], [total_money], [status], [created_at], [updated_at], [deleted]) VALUES (19, N'C20', N'7e073e67-0c6b-4010-8ae0-c9688218857e', CAST(N'2023-11-08T00:00:00.000' AS DateTime), 6, CAST(830000.000 AS Decimal(20, 3)), 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
SET IDENTITY_INSERT [dbo].[cart] OFF
GO
SET IDENTITY_INSERT [dbo].[carts_detail] ON 
GO
INSERT [dbo].[carts_detail] ([id], [quantity], [id_cart], [id_product_detail], [status], [created_at], [updated_at], [deleted]) VALUES (1, 1, 0, 0, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[carts_detail] ([id], [quantity], [id_cart], [id_product_detail], [status], [created_at], [updated_at], [deleted]) VALUES (2, 1, 1, 1, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[carts_detail] ([id], [quantity], [id_cart], [id_product_detail], [status], [created_at], [updated_at], [deleted]) VALUES (4, 1, 1, 13, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[carts_detail] ([id], [quantity], [id_cart], [id_product_detail], [status], [created_at], [updated_at], [deleted]) VALUES (5, 2, 2, 16, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[carts_detail] ([id], [quantity], [id_cart], [id_product_detail], [status], [created_at], [updated_at], [deleted]) VALUES (6, 1, 3, 20, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[carts_detail] ([id], [quantity], [id_cart], [id_product_detail], [status], [created_at], [updated_at], [deleted]) VALUES (7, 1, 4, 22, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[carts_detail] ([id], [quantity], [id_cart], [id_product_detail], [status], [created_at], [updated_at], [deleted]) VALUES (8, 1, 4, 1, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[carts_detail] ([id], [quantity], [id_cart], [id_product_detail], [status], [created_at], [updated_at], [deleted]) VALUES (9, 1, 4, 26, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[carts_detail] ([id], [quantity], [id_cart], [id_product_detail], [status], [created_at], [updated_at], [deleted]) VALUES (10, 1, 4, 27, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[carts_detail] ([id], [quantity], [id_cart], [id_product_detail], [status], [created_at], [updated_at], [deleted]) VALUES (11, 2, 5, 15, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[carts_detail] ([id], [quantity], [id_cart], [id_product_detail], [status], [created_at], [updated_at], [deleted]) VALUES (12, 1, 6, 14, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[carts_detail] ([id], [quantity], [id_cart], [id_product_detail], [status], [created_at], [updated_at], [deleted]) VALUES (13, 2, 7, 18, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[carts_detail] ([id], [quantity], [id_cart], [id_product_detail], [status], [created_at], [updated_at], [deleted]) VALUES (14, 3, 8, 29, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[carts_detail] ([id], [quantity], [id_cart], [id_product_detail], [status], [created_at], [updated_at], [deleted]) VALUES (15, 1, 9, 22, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[carts_detail] ([id], [quantity], [id_cart], [id_product_detail], [status], [created_at], [updated_at], [deleted]) VALUES (16, 1, 9, 24, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[carts_detail] ([id], [quantity], [id_cart], [id_product_detail], [status], [created_at], [updated_at], [deleted]) VALUES (18, 1, 9, 14, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[carts_detail] ([id], [quantity], [id_cart], [id_product_detail], [status], [created_at], [updated_at], [deleted]) VALUES (19, 1, 10, 2, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[carts_detail] ([id], [quantity], [id_cart], [id_product_detail], [status], [created_at], [updated_at], [deleted]) VALUES (20, 1, 10, 16, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[carts_detail] ([id], [quantity], [id_cart], [id_product_detail], [status], [created_at], [updated_at], [deleted]) VALUES (21, 1, 10, 18, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[carts_detail] ([id], [quantity], [id_cart], [id_product_detail], [status], [created_at], [updated_at], [deleted]) VALUES (22, 1, 10, 19, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[carts_detail] ([id], [quantity], [id_cart], [id_product_detail], [status], [created_at], [updated_at], [deleted]) VALUES (23, 1, 10, 22, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
SET IDENTITY_INSERT [dbo].[carts_detail] OFF
GO
SET IDENTITY_INSERT [dbo].[color] ON 
GO
INSERT [dbo].[color] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (1, N'CL01', N'Đen', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, 0)
GO
INSERT [dbo].[color] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (2, N'CL01', N'Đỏ', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, 0)
GO
INSERT [dbo].[color] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (3, N'CL01', N'Hồng', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, 0)
GO
INSERT [dbo].[color] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (4, N'CL01', N'Xám', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, 0)
GO
INSERT [dbo].[color] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (5, N'CL01', N'Be', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, 0)
GO
SET IDENTITY_INSERT [dbo].[color] OFF
GO
INSERT [dbo].[customer] ([id], [code], [name], [birth_date], [gender], [phone], [address], [status], [created_at], [update_at], [deleted], [wardcode], [districtcode], [fulladdress]) VALUES (N'64ac93b6-a093-4f7a-84ff-050e5773385a', N'CE01', N'Hương', CAST(N'2003-08-16T00:00:00.000' AS DateTime), 1, N'0973554745', N'An Thái', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, N'13010', 3440, N'Phường Xuân Phương, Quận Nam Từ Liêm, TP.Hà Nội')
GO
INSERT [dbo].[customer] ([id], [code], [name], [birth_date], [gender], [phone], [address], [status], [created_at], [update_at], [deleted], [wardcode], [districtcode], [fulladdress]) VALUES (N'79a3a3c5-4e10-46a3-855a-080aee7fb16d', N'CE02', N'Thanh', CAST(N'2003-08-16T00:00:00.000' AS DateTime), 1, N'0987654321', N'An Thái', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, N'13010', 3440, N'Phường Xuân Phương, Quận Nam Từ Liêm, TP.Hà Nội')
GO
INSERT [dbo].[customer] ([id], [code], [name], [birth_date], [gender], [phone], [address], [status], [created_at], [update_at], [deleted], [wardcode], [districtcode], [fulladdress]) VALUES (N'e1211807-d831-4b42-b62f-09ff2f9397a9', N'CE03', N'Mai', CAST(N'2003-08-16T00:00:00.000' AS DateTime), 1, N'0975123456', N'An Thái', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, N'13010', 3440, N'Phường Xuân Phương, Quận Nam Từ Liêm, TP.Hà Nội')
GO
INSERT [dbo].[customer] ([id], [code], [name], [birth_date], [gender], [phone], [address], [status], [created_at], [update_at], [deleted], [wardcode], [districtcode], [fulladdress]) VALUES (N'950fcc94-e428-4078-8d45-0e3887c384d9', N'CE04', N'Hoài
', CAST(N'2003-07-22T00:00:00.000' AS DateTime), 1, N'0934567890', N'An Thái', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, N'13010', 3440, N'Phường Xuân Phương, Quận Nam Từ Liêm, TP.Hà Nội')
GO
INSERT [dbo].[customer] ([id], [code], [name], [birth_date], [gender], [phone], [address], [status], [created_at], [update_at], [deleted], [wardcode], [districtcode], [fulladdress]) VALUES (N'99903190-37a9-48fd-b8e0-1db1779736fe', N'CE05', N'Thịnh', CAST(N'2003-08-16T00:00:00.000' AS DateTime), 0, N'0335535229', N'Đại Đồng', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, N'13010', 3440, N'Phường Xuân Phương, Quận Nam Từ Liêm, TP.Hà Nội')
GO
INSERT [dbo].[customer] ([id], [code], [name], [birth_date], [gender], [phone], [address], [status], [created_at], [update_at], [deleted], [wardcode], [districtcode], [fulladdress]) VALUES (N'5e95507c-932f-4a64-90f0-3308f66e2edc', N'CE06', N'Nam', CAST(N'2003-08-16T00:00:00.000' AS DateTime), 0, N'0934567890', N'Đại Đồng', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, N'13010', 3440, N'Phường Xuân Phương, Quận Nam Từ Liêm, TP.Hà Nội')
GO
INSERT [dbo].[customer] ([id], [code], [name], [birth_date], [gender], [phone], [address], [status], [created_at], [update_at], [deleted], [wardcode], [districtcode], [fulladdress]) VALUES (N'ca69225b-b192-41f9-a056-384f94970ea0', N'CE07', N'Khánh', CAST(N'2003-08-16T00:00:00.000' AS DateTime), 0, N'0921345678', N'Đại Đồng', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, N'13010', 3440, N'Phường Xuân Phương, Quận Nam Từ Liêm, TP.Hà Nội')
GO
INSERT [dbo].[customer] ([id], [code], [name], [birth_date], [gender], [phone], [address], [status], [created_at], [update_at], [deleted], [wardcode], [districtcode], [fulladdress]) VALUES (N'40162844-7d69-4736-b520-3a8d546bd176', N'CE08', N'Hùng', CAST(N'2003-08-16T00:00:00.000' AS DateTime), 0, N'0955678901', N'Địch Vĩ', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, N'13010', 3440, N'Phường Xuân Phương, Quận Nam Từ Liêm, TP.Hà Nội')
GO
INSERT [dbo].[customer] ([id], [code], [name], [birth_date], [gender], [phone], [address], [status], [created_at], [update_at], [deleted], [wardcode], [districtcode], [fulladdress]) VALUES (N'41f91c39-1036-40e8-af4c-4b0605ca45bd', N'CE09', N'Quỳnh Anh', CAST(N'2003-08-16T00:00:00.000' AS DateTime), 1, N'0918901234', N'Địch Vĩ', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, N'13010', 3440, N'Phường Xuân Phương, Quận Nam Từ Liêm, TP.Hà Nội')
GO
INSERT [dbo].[customer] ([id], [code], [name], [birth_date], [gender], [phone], [address], [status], [created_at], [update_at], [deleted], [wardcode], [districtcode], [fulladdress]) VALUES (N'c6626b2d-d721-4285-bf06-51930e7d2ac3', N'CE10', N'Hằng', CAST(N'2003-08-16T00:00:00.000' AS DateTime), 1, N'0912345678', N'Hòa Bình', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, N'13010', 3440, N'Phường Xuân Phương, Quận Nam Từ Liêm, TP.Hà Nội')
GO
INSERT [dbo].[customer] ([id], [code], [name], [birth_date], [gender], [phone], [address], [status], [created_at], [update_at], [deleted], [wardcode], [districtcode], [fulladdress]) VALUES (N'634cb5c8-f1a0-44d0-b8a9-5298e04255d8', N'CE11', N'Trang', CAST(N'2003-08-16T00:00:00.000' AS DateTime), 1, N'0975123456', N'Hòa Bình', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, N'13010', 3440, N'Phường Xuân Phương, Quận Nam Từ Liêm, TP.Hà Nội')
GO
INSERT [dbo].[customer] ([id], [code], [name], [birth_date], [gender], [phone], [address], [status], [created_at], [update_at], [deleted], [wardcode], [districtcode], [fulladdress]) VALUES (N'8d422546-4820-4ccf-9d30-575fe485d3ac', N'CE12', N'Hiếu', CAST(N'2003-08-16T00:00:00.000' AS DateTime), 0, N'0901234567', N'Hòa Bình', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, N'13010', 3440, N'Phường Xuân Phương, Quận Nam Từ Liêm, TP.Hà Nội')
GO
INSERT [dbo].[customer] ([id], [code], [name], [birth_date], [gender], [phone], [address], [status], [created_at], [update_at], [deleted], [wardcode], [districtcode], [fulladdress]) VALUES (N'3a6863ba-ce82-4eee-a19b-6653e350d26e', N'CE13', N'Hà', CAST(N'2003-08-16T00:00:00.000' AS DateTime), 0, N'0921345678', N'Địch Vĩ', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, N'13010', 3440, N'Phường Xuân Phương, Quận Nam Từ Liêm, TP.Hà Nội')
GO
INSERT [dbo].[customer] ([id], [code], [name], [birth_date], [gender], [phone], [address], [status], [created_at], [update_at], [deleted], [wardcode], [districtcode], [fulladdress]) VALUES (N'77d0ef38-3949-4c93-b096-8a9b09e6ab5f', N'CE14', N'Cường', CAST(N'2003-08-16T00:00:00.000' AS DateTime), 0, N'0987654321', N'Địch Vĩ', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, N'13010', 3440, N'Phường Xuân Phương, Quận Nam Từ Liêm, TP.Hà Nội')
GO
INSERT [dbo].[customer] ([id], [code], [name], [birth_date], [gender], [phone], [address], [status], [created_at], [update_at], [deleted], [wardcode], [districtcode], [fulladdress]) VALUES (N'be8cc949-a72f-42a0-898e-99618bcd4650', N'CE15', N'Lan', CAST(N'2002-01-01T00:00:00.000' AS DateTime), 1, N'0955678901', N'Địch Vĩ', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, N'13010', 3440, N'Phường Xuân Phương, Quận Nam Từ Liêm, TP.Hà Nội')
GO
INSERT [dbo].[customer] ([id], [code], [name], [birth_date], [gender], [phone], [address], [status], [created_at], [update_at], [deleted], [wardcode], [districtcode], [fulladdress]) VALUES (N'ae9884e4-0e44-47c2-b356-abb655117c71', N'CE16', N'Ngọc', CAST(N'2002-01-01T00:00:00.000' AS DateTime), 1, N'0921345678', N'Địch Vĩ', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, N'13010', 3440, N'Phường Xuân Phương, Quận Nam Từ Liêm, TP.Hà Nội')
GO
INSERT [dbo].[customer] ([id], [code], [name], [birth_date], [gender], [phone], [address], [status], [created_at], [update_at], [deleted], [wardcode], [districtcode], [fulladdress]) VALUES (N'f81555a9-22a4-4679-a3d9-bb497179d059', N'CE17', N'Chiến', CAST(N'2003-08-16T00:00:00.000' AS DateTime), 0, N'0975123456', N'Đại Đồng', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, N'13010', 3440, N'Phường Xuân Phương, Quận Nam Từ Liêm, TP.Hà Nội')
GO
INSERT [dbo].[customer] ([id], [code], [name], [birth_date], [gender], [phone], [address], [status], [created_at], [update_at], [deleted], [wardcode], [districtcode], [fulladdress]) VALUES (N'7e073e67-0c6b-4010-8ae0-c9688218857e', N'CE18', N'Bình', CAST(N'2003-08-16T00:00:00.000' AS DateTime), 0, N'0973554745', N'Đại Đồng', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, N'13010', 3440, N'Phường Xuân Phương, Quận Nam Từ Liêm, TP.Hà Nội')
GO
INSERT [dbo].[customer] ([id], [code], [name], [birth_date], [gender], [phone], [address], [status], [created_at], [update_at], [deleted], [wardcode], [districtcode], [fulladdress]) VALUES (N'3026a5bd-e215-4edb-8a32-db71c1a7d750', N'CE19', N'Thật', CAST(N'2003-08-16T00:00:00.000' AS DateTime), 0, N'0955678901', N'Đại Đồng', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, N'13010', 3440, N'Phường Xuân Phương, Quận Nam Từ Liêm, TP.Hà Nội')
GO
INSERT [dbo].[customer] ([id], [code], [name], [birth_date], [gender], [phone], [address], [status], [created_at], [update_at], [deleted], [wardcode], [districtcode], [fulladdress]) VALUES (N'03bef975-baa5-43ad-ab1f-e4826eb049d6', N'CE20', N'Tuấn', CAST(N'2003-08-16T00:00:00.000' AS DateTime), 0, N'0918901234', N'Ba Đình', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, N'13010', 3440, N'Phường Xuân Phương, Quận Nam Từ Liêm, TP.Hà Nội')
GO
INSERT [dbo].[customer] ([id], [code], [name], [birth_date], [gender], [phone], [address], [status], [created_at], [update_at], [deleted], [wardcode], [districtcode], [fulladdress]) VALUES (N'30a995ca-109b-4254-8fa0-ec50d675de18', N'CE21', N'Việt', CAST(N'2003-08-16T00:00:00.000' AS DateTime), 0, N'0973554745', N'Ba Đình', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, N'13010', 3440, N'Phường Xuân Phương, Quận Nam Từ Liêm, TP.Hà Nội')
GO
INSERT [dbo].[employee] ([id], [id_roles], [code], [name], [gender], [birth_date], [address], [phone], [email], [status], [created_at], [update_at], [deleted], [cccd], [image], [password], [wardcode], [districtcode], [fulladdress]) VALUES (N'33187a7d-d77d-4000-9483-03c4c927065e', 1, N'EM01', N'Huyền', 1, CAST(N'2003-11-11T00:00:00.000' AS DateTime), N'An Thai', N'0973554745', N'huyen@gmail.com          ', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, N'001203029222', N'data:image/jpeg;base64,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', N'3333', N'13010', 3440, N'Phường Xuân Phương, Quận Nam Từ Liêm, TP.Hà Nội')
GO
INSERT [dbo].[employee] ([id], [id_roles], [code], [name], [gender], [birth_date], [address], [phone], [email], [status], [created_at], [update_at], [deleted], [cccd], [image], [password], [wardcode], [districtcode], [fulladdress]) VALUES (N'5de1e4f2-a94a-4201-9085-06acdff42ca8', 0, N'EM02', N'Ly', 1, CAST(N'2003-11-11T00:00:00.000' AS DateTime), N'An Thai', N'0973554745', N'ly@gmail.com             ', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, N'001203029222', N'data:image/jpeg;base64,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', N'2222', N'13010', 3440, N'Phường Xuân Phương, Quận Nam Từ Liêm, TP.Hà Nội')
GO
INSERT [dbo].[employee] ([id], [id_roles], [code], [name], [gender], [birth_date], [address], [phone], [email], [status], [created_at], [update_at], [deleted], [cccd], [image], [password], [wardcode], [districtcode], [fulladdress]) VALUES (N'c65ac0ad-e17a-42b8-aa94-07465f17ace7', 1, N'EM03', N'Hảo', 1, CAST(N'2003-11-11T00:00:00.000' AS DateTime), N'An Thai', N'0973554745', N'hao@gmail.com            ', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, N'001203029222', N'data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAoHCBYWFRgWFhYYGBgYHB0cHBoYGhocGBweGhoZGhwaGBocIS4lHB4rHxoYJjgmKy8xNTU1HiQ7QDs0Py40NTEBDAwMEA8QGhISHjQhISExNDQxMTE0NDE0NDQ0NDQ0NDQ0NDQ0NDQ0NDE0NDQ0NDQ0ND82ND80NDQxPz80MTQ0Mf/AABEIASoAqQMBIgACEQEDEQH/xAAcAAACAwEBAQEAAAAAAAAAAAAEBQIDBgcBAAj/xABDEAABAwICBwUFBAgGAgMAAAABAAIRAyEEMQUSQVFhcYEGIpGhsRMywdHwFEJysiMkM1KCouHxBxU0YpLCFrNTY9L/xAAYAQADAQEAAAAAAAAAAAAAAAABAgMABP/EAB8RAQEBAQADAQEBAQEAAAAAAAABAhESITEDQVEyIv/aAAwDAQACEQMRAD8A7Mhcae6PxBFIXG5N/F/1csz3DCwRAVNBtgrZWZ6viVVVqBokpXjtItYJe/UGwWn+p4LMaurAbV43ENORWHxva6kDY63EkyOcTsG5AU+1LXuILgLSABc9XRdDrOkuqAIetjmt48lhGdrCO665v5bcln9MaffUMB8DaAbf1W6PHR63aegCRr3G4Ex1CJwmn6DzDajSdoyd1C4diMU6YcY2CLC2VhzQ32lzTIF97SQ4cjtQto8d9088HDV8/wBm/Yf3SuOOV+je11UU30y8PY9pYQ+SWzIykEHNVEh0DI7LzPLehb0czgrQ37en+I/lK3LQsPoZv6en+I/kK3TQqY+Jft9U4kd1I+yo79bmPzPT7EiyRdlB363Mfmenv8Jn5WjhJe0Y/RP6fmCeJL2jb+ifyH5gsWfRPZ0fq7P4vzuTLVS7s2P1dnN/53JqtB3/ANNMhsZk3mfyuRKHxZ93n8CprLGZBfPeGhfEwFke2PaQYduq0gvP8uyfWBvvsWrR92i7StokhsOflfJnF538Fz3G6QfWJc+pLdp2Hl80oxGNfVdcnVzjaeJO9fPrWDQLT0QNPTzG1WNbYHqT8VHDt1ruEESOn16oZ7Nd8nLII17w0eSW0ef0S+vIAcbjbsKFfnP1dCPqHLdkvm1N/wBcEYCb3TnkoERkZHWets141+YN/VekRyOzcha0QNGTrNOq7fsPNF4LGX1Hj++VjsVbWRyP1KnUpawG/l9eCAtBgMZ7N7XOGsBcHoRE77rd4TFMe0PYZHmDuI2FctwmJMBjsuuzntG/aOITPR2LfRfrMdbcciNzhtRzrhdZ8nQMRksdgqldpf7HWJ1u9q6uUuj3uq1GHx7KrNZtjbWbtafiOKWdmm3rcx6vVbe8TzLOlx0vig7UcXNdEifZ32AC2ahjcXiS39Ix+qYBnU2mNl80TpMfrDT+D86cdoW/o38vit96NvOehnZpv6uzm/8AOU11Es7Nf6dnN/53Jumnwm/+jpjwZjZZA6Y91p3PB/lcPkiMO33jqxc9bm6p0oZYOfwKl/FQ2lNJilSc8/dFuJOQXDtNaRdVqOc4ze/yHD5Bavtvpcub7MGzbc3ZQeSwzmfMpbem5x611l8wSQNy+ZTkpjhsLlbahdcNnPVNDCyTxhW1MKbp5hsLAyUnYW+WYnw+gp+avgydakUKWkXWpxOAvkltbBIzRbkvY2RxVrBImMrH1HxUNXVcQjWUwYdvz+aYnFdNkiPBW0W31TacjuIVjKSsdTn681h4pqUDfY4bvUIrDVNdvIw4bjsKto94X95tuYQ1UBjteYaYa8cDk48j8Vm4Z4DFOpuBB6bxu5LRdnGQam52qR1Lz4rOMpBzcrjL4hMNC44sdquMtNidt/dPqOiaUup6F6SYz7TTLnd1mqXj/aHiJHj4Jn2l9x/L4hIGv18Q55Y7WYGWkjXBdA5kEmx3Jhj8c6rh3ucwsIMQ7gRcbxdPlK/w77N/6ZnN/wCdyayUr7Mt/Vmfxfncm6eF19MWVpBBEOAMjcfkdiRaaxraVOGiNQQ0HfBDdu/0Tusw+83PKMpG75LBdvcbD9Tc0OPhA9T4qVq0jAaVra9QNz1bniTvQpF1Cg4lxdvv8kTRpyev9UB4tw1CVocNhrBA4SlcBPaLICjq+18zkWUKS+qlus0SNbdtg/2VGIeS8MYHvLhJaHkRcXmbDPl4BeaQ0E2jhnP1nF8sMgmBBgBs3yJujn87Qu5me1mIw8hKcTQ4c0RhmvYWEOcWu1Q4OM2dabzz6HLaZjKG0bfVC58afss7P6xePoEGURhDITDHUgbkJSzuz4pp7iWpynFBglTfR+RO5V4J2tcbUwDJEZrMApNLHTsyPz6K/FUAW5SCDbYRtB+t6kWWBOyx4j6lFUGd3VN/ls8pRgAdD1ZaWn3md0z/ALcj1bCLrUdV5I2y4f8AYfFK2v8AY4pk2a8Fh3azTbxCfvpzaLtJjpMdCFrGj1j3OcHNgksMTtezvMB4WROkaxfhi457REFp1rtPEZcc0I2nBgQJ77eBFnDzPihMTh3hj3uMhzRmSSe82DyiVTNS3n+tt2b/ANNT5O/9j02hJuzDv1anyd/7HpvKtEd/TTEGGuPD6K4l2uxxe97p990D8Iy8oXX+0WILMO8gwSNUfxED0JXDNMPlw6nzI+Chr6vmA8KbHwR2GbcIDD5dfgUfTdEfXBLaeT2d4Jl06dT7hGVjcbLZpBgsZEWT6jiQ5Sv1aDtD6IYymLka5k6pgkCYaSNmcwc5VfausG4dzPvOjL7oBmTzIA8V6xznMDddxYMm2GWQLgA4jmUq0sxrmuYAWgEHuXLjGREXI5q/nOpX89XqRafZsDo1jqs7uXdBaXDq8DoVbj61oj+i9wtB0NLzdoho3DeeP1ysexR1rtVznxzIzOIa52e+49UvrUCNlxfoFp67BMoDHstO4fBaaDWQWjXhroOR7zeo+vNMHQ115IJlp55tMC97pbhhLRBuy3TMeUp/Qgj6yI+aa+yyJFgA1osc+H18F5REGNoy5bPiiG7vr6zKGxLdWHbjHQ2HgYHgsFLu09Duse0ZOB6tmPEEjonGGrBwY4R3m58wCFHF0g+i4X90zvtw4X8kBoGpNFzD7zbbbFjrjwcOgG9U50hsWRcZg649HDrKA0y97YbPcMEACZ7wLmnls3SmD6gtO0WHBwAPnChisNr0THvMgiNuqRa2ctW4KvA6ffSY1jWBwbth+0k7Lb0T/wCW1P8A4R/OmPZ6g04dhIH3x4PcEz+zM3DwVpPTnt930p/xAxZbTa0Z3MbyRqtA43cei5V2gphlRzB9zuGMpYS0+kroWnq/tcZTZFg5zyJ2UwdX8oP8a5zp5xNR7j95ziOPePmue32vn4Ca631uTLCU5jkPO6VMOfJPMFl4fXml1fR8ztMcPhQmVNsIXCRGaKJUrVeDcK7YvGYeCTrGDNuZJuepQ9CrBRoBIWmr/DfE9iHqOROpZCVBdBpFFcAgoWq3u+74wF9UqOc8sZAIzcdnLisJjcW973gueSCRBcYBDots2FPnPSa1M+mmw1Aa5AkNLTkRsdLT/MfBNMMIgcxuWa0Nh3Buu43a8ATtkZeBKdUHOg3vM+Fj6g9E19Fz7N82yDBbfwzHgoVnB7S07jszEQVLDOG29rxyshsfTIGsDBacx5HlCHeD49Q0Vir6jjBB1XcYydyI1SgqLTRxL2j3KjS5okwC3uuHGx/lVRqy8EGHCzhu3Hx8iVLG1SQx5iZBHB15b1FlWaTuRtTEe44cWndytuM+ARWAx2q8j7rxPUD5EeCTuf3XgbmvG+RnA45qhlfdm3z/ALj0S9Z0rQTm02MJPdDnxn95zi3IcQnn+Zj90+J+SzHZp4fShwBAMjx/snH2Rn7g8F0T3HHvs1WcwwmpVqAHusaxs/8A2GI5hrWrnGlnd/KJHzXUWtc2nVccw4H+IEGMr2Hi5cw0wD7UzOU32zJlc9dcAMNzyTnCVAGEnZBnoPkkbfeKf6MwYqMvkAD8ENKY+ptx9XUe9ga1jASZu47glje0laJLmP4FsW/EDIPRaWnhQ1jmEazXSCOBSZvZhhcBrP1dwA1jwLvjCEuf6a51300WisSK1MPFjaRzEp5g3ReAeaW4HDNYzVa0NAEQOAR9NynfvpXObZ7X1HXVDmBWEjViDM5zaOS8DUB8SlmA9mQ5jj1EzzSrHaBZUeXlzmknWcG5E74IMLXU6YMh23Iqo4e/BNNcLcS/SXBaLa0BjQTedtzvJNyUT9jLSbXCa1KYabGeIVTm70LRmf8AC9jIMDavsU8NbeTOwXJT7EvpuYIB1jy1gW27xQDMMHvax0QYFxa5M227D0C29TObfoyeu1jMfTAdrTDHWJvHAu3RldVl7tV9J/vDI8dkcD8U00rhgwvYQJa4gbAeB3c0sDS9jXgS5nduRJaPuuj7zfMKmNeWeufc9vsM+WseM2yx3qF6WQXjgCI4ESB/CZVLHwTYQe9F88iiKoEsdkHgDydfnAA6FErY9iMRLQNskHqGx5tK2equd9iKkF98i3PmRbxC6JHLxXT+fxyfpP8A1SPFNJw9Z2esXuPVojwMLk+kT3zyaDtuGifOV1jTT9Wk9lxrVGgbzrNafCxXItImHu3AuA5SY8lC/XRPhc18ElbDQDtWkCciAfPJYh7oBPBbjsbD8Mwm5aXNz3OOfSEupydX/HlvDCoXNIc6CD90bBz8EzoUY2Ts357Vc2nkr201Lq/FJZCrZKvr5FQY6ED5npKF6FJrgrn0JbIKzVBq+cq6RlWtCPCqXNXmpKtc1WMpygPY+qYUsMEg7bKvE4YkAt94ZfFGMpq4UbyjyX1fhNa5GL7Q4chl/eNzz4cMgspgsW6nUg+67VkniIk74JzzhbXtjZlswsS9he0GDMZZyDb0nwVMSSciO/fsxrU7zuz33sTH1tXjJcyNoILT1kjx8iUJo/EawEnvCWzkC5sSOTmw4cQd6JpxB4XjdYz0gk9EaT60PZEH2lYRbVDhPG46z5ro+quddlzFR4i+pG/3XZHlFua2+vxXR+fxy/p/0Qds8RqOJB7wAjmX3PQavRxXLsWZJlantLpJ1SGvILwYfExLQAXDfI1fBZXFncorT4UYt2zefRa//DnEftae4h45OGqfNo8VjMS6Xck07J40UsWwkw15LD/HEfzBqbU7ng4146ldfa1TjgvmBWBi5pHXdhMSO7F/7JZTruLiC1zecEHkQU+LAqRh2g2W42dllVlRzSKbg0/vET5FE4ao9rA17tZ8QXAQDxjIIxjQptYFuDdB6TICvaxWNYpBqKfkrDVbTYpBqsYFmuk2MVpavGKb8kS2sJ2/rBjCTu9ViNG4tz36hgAS22eYIPg4/wDErQ/4nYoaoZ+84DoL/BYzQ2JhzTF2QCN7A6x6SQeBHFUxPXU9698OdSHxk5w6F7LtI3TccncEXrXDhnu37VHSVE32OZ328QDrQDvz6L5jw4SPDzBnfBjotWy0PZl4+0CMi2J2m7c+MLWe05+KwuhapbVbvvH/ABd9dFvdRu8KmPjn/Wf+nMtI1RUqueBAaNXnFp5myS410BMmuhsZkyfrz8Usx4sPFTlWsJ37VBxIMgwRBB3EXBV1QKkqpHauz2kRXoMqbXNE8HCzh/yBThrlzH/DvSoY52HcY1jrs5x32+AB8V0YPsubc5XRm9i19RB18UG5lCYus8ElgBPEwsvpE4km2qPEn4JbV/z/AD8q1rMe3ei6GLY77y50RXNtYDi0X80XhsLWt+kPgCfRDyXv4R0Fz42r2nWWWw+FquEGo+OED4JpgMEWD3nH8TiT4lHrm1iZPGuVrEJTeiWORIvBVeKq6rSV7rLMdrdMCkwgGXGwCwcc07caS9piC0GQy3U5/BI8I8tIcMwfI2P1xUKzy5zibkkmeq9oOggm42jeDYrok5OOfV7rrcY46zGPaL2kb4gR1BhC4V4i/wB06ro2jY7nqnxRmiyHUA03hocOOqL+TUrwr+85u4ub0BkeRSVSfTjDP1S121rgRxE38k9/zBm+p5f/AKWea7LiPqOiZe1ZuH10WnQ1O1m6e87B85+CGx7pTd+CcLOItsF42JRjhf64oyF6VvbbiqNyJeVQBY8FQrxj3NcHtJa5pBaRmCNq6v2b083E0wbB7bPbuO8cDmFygq7R2kH0Hh7DcWIOThta757Ems+UNnXK7WxgKDxmj5yVOgNMsxLA9meTmnNp3FPWiVz2fx053c+4zrdHb0bhsEBsTX2AXrKIGSHipf21Yqp0o2K3VVoYvdRMlbVIarGlekJZpHSLWDeTkAsEXaT0k2mwkm+wbSua6dxTnlz3cYG4Jziqj6jtZ/hsCzXaKpqtgbbJse62vUrNAL1oUm+i+hdDm412gKncbwMeJIHmQqcXR1K79znazd2Zt4aw6BR0IbEfVx84THTFMkB2cQfUH4qV+qT4g0iLbclZB3+iob7rTNiJEei91+B8EDeh/aCtqvkffaD4k/ILL4h8yU67UVBrti0elo6ZrPVH3T8SUEKllir1S8JwrwiFW4K5yiWrMu0PpR+GqCow8HN2ObuPHcdi7DoXS9OuwPY6QcxtadrXDYVxJwRGjdJ1cO/XpP1TtBu134m7eeaTWfI+dc+u8mqvW1Que6K7da8NqM1XcDLTym4ToaeByB8lCyz66M8s9NY2qN68qVwBcrK/5y45NjmVU/Evf7x6bEOt4muP0pmGeOxJXgkycztV7WKLxCLA6/daSsJpatrv4BazTdeGxkFi6rpJKp+c/qP61TtVjWqvaiA1WShzop1yN/wTrEkuaRt1JHNt/gUhwDocPrYndR5tETmFK/VZ8LcG/WbAyJNp9133hymSOau1T9FCu/RVpAllQawE9CLbQZCZfof3neSzEWkcWXvc47fG1r8UKH5FeNu5H4bRusOO7mnTAwoFqueInwUEzIaqgVN7oXgSlQc1DPai4VVRqMZSwkGRmtfoTGh7QDmsiQi8BXLHghDWZqHxq5rodKmjGMASnR+KJa0kEA5E2lNGvXPc2Ojy6se5UPCvKprmAsLK9oq0SFmSU70q1z3GAYBEnYJy6pI8QSFfM9Ofd7UGCSEW8KikLq55TkHYU5dPRPW3HRIKBy5J/hn93kFK/VM/Addmsy+zvA7g6AegdBPAncoeyP7vmiXyAYHd1+kOlwHqOcKz7Ozf/KixG3CObDiCj8LioBHDZYxwOxaetg7OY9tx8rEfWxZjHYLVBIO/rG7wTUkKXbV8xtjKkWkK1lI2Q6NgNzZsqgYt4IqozaoVKciU0LxVxXtZsiV9TOxScLHciwMqTQpOavWLBW07G6ac0ezce7sByA3/AD4X2LeUWMcO9TYekei47gapY4OGY+oO8LqnZ7GtqUwby2x28vKE0nQ7TIaOon7hHJzvmqMVoylq+6483H4JowKrENEfJDxg+V/1hO0OG7mqxoawXAHW53lc/wAQ2HkLremcMHMOyW/A3PiuV6RpQ8/WVkLONKrwzLlWO2rymIEr3aAsK+kbjonVE90pMz7vEJxhzLbKd+qZ+DcI0Opv3jVHm4heey5KGjSLtNg6ARfYbQfrNMvsp/eb4FDrNNpjCyNbaNnr/VYzHXtvIHSRPouiaSZI1py2bOawukWfpJERy8/MJ9EyztWjBjiVY9lo4WROIZ+k8FXXHd8kpyus3cFCibxvsiGwULWZB+tiaFqiswtdC8DthyVmOMid0KlhlMDx1PerKbQItYqTHAiCvHPgQsD6nAcRs+a1PY/GOD3MBvFhOcES3+ZZAvumehnE1QW5jVd5gHyJRlB1/DYhxEyI3qyo8kbEBo+7c+nO6JqHYDfftCYoTSRGoWzJPptXPe0mjtU6w2Loj6Qust2oA1b8I88/FDQxiH2ACiMx9bF64y5fE94dUoig3ut6Jlg3WKXsHcHRE4J+fJT0rm+jRjc7XzCZ/aOfilIfccvRT+0LcF0nFEQQenMrJY+gQXcI8yb8lqccb8kixLQ7WO0Dfnc/0TUmWUxNM6/1sQ+JdAB2H1RWIcNeCbTB5RfzsluMcC4gbClFUBF98KFV0yJibg7juK9puVb2FGBVFfaLZZhCUnI2q2CDvn0QT2EEHenBK4MhS1w4Qc9nyXwZPJUVJBWBF5Wn7GYAvLnxbWA/4w63WAs5Tpl5AGZMeK6r2UwIp0mNjYZ4m0u8VgpzhGaohfUnzJO0q97IB5IOg6ybpU6zlhe1eKvq528yYH/YrZ418MMZ5DmbLm2m64dWduaY/wCIj5pbTQteyIHioA35C/W6m921QYYaSTmsNHUj3Ap4V8Kim6w4qdPNJo2TKm+YO7MfJXyzil9BpMwJ+gVfqP3IN10/Eu1ZBzSOo/VdfIgjztPVajTeGEyM4WTqjWcQbd0/BNYErO6ToOD36ouBaBPks4Nca2u0jjEcFrsbTe0B4EzJmxMHKfBIMWxzg4EEW/shGoPBukkLynWu7hn848lPBU3NeCcpHoqn4fVeTO2OibgeQljwQZ2b1czR7KjYYQ1+eZIdGcTkUK0QH7IH9VXhK4a4Ekji08N3xEID6UEFp1XefwU3Uwc+hUsU7XuSCN4+N1UwuHuukG0EIwLVtOiOq0Wi+1FWhDXtFRsWmz44OGfULLUK5DuE3Cf0GgiCJG5NE7rjXUe2OHe0awewumAW6w5yybTbLYVfh9J0SLVGdXAHwMFZR2jmTYERYXta2R8epQeJ0duPiEeBNytRpvFDVht4EyPD4rn1Z1zP1v8AOVbWovZYGx3EjxS6o8zG5JxSali5z59F63MDYFSx9lNhhERIdYIljT5IagJRzQp00GYQNa1z3bIA3lxDoA+PBBfaH/QCc0NAVngHVscpgbuo5o3/AMRq72+JR8aXsdM0qPdPRYHSlcCpqjN3dHNxhdB0r+zcf3brluFf7TFOefdaSR4ao85T0IdaQaC0N6JBXZctI6ck/qmSk9dskrSE1rhVXZG7JBV2AkRkU0xVOx+KXaq3CzQJzD8EO7CvBsPBMwxXU271uDdUBhsGTmp4lzWCwlxy+aOc0nIx6qNPCNF8ydpzTcJdf6D0TgQ7X17OcCWG+bQSWx/uEjoN6ZYUDbs8T8lGpTEQmOIqCtqOYwNrZPDYDakC1Ro2PIBkbYB2oyNb16HyoVQoMqAWPdIzDrEcwV894dZpBJ42HElEnKCrsmeXns81nMRQLStUGZIfFYVrhldLYfOuMq1WByOqaOIyIhVnCxckR9ZJeLTUq7CtyWg0Jop1Z42NEGTtuPJAaCwofUaCO7InjwXT8Hhg10NERAAHJDOe+2u/fDOrR1RTYLNayeZJMeQXnswp4+t343NaPU/FCe2KoB1i6OvTez95jh1LSB5wuNaJ0nTYXh7wx1s59QOa7Zh9nNfm3S37ep+N3qUlGNiO0lJzwxms8uIEhsNBNvvEHyRFMSsXoH9qOfwK22GzTRH9UMXT7jjwSILSYr3H/hKzYRpM/EmhTCi3NTCw16ArAoNUwsWoEL6FJeBYYMZin92Xk6vukwSOEm8cFKtVc+C4kkCBMIamrli9RLVB7VeqSiwStg2u5oN+j73/AKpsVXtQNNUy7PYTVIMDZ6hbage8Tx84Czuhsm81PQtQ/a8WJMTTMTafZtvCP8DGrdU4xmI754kegXntAhMV+0P1sCkkdL//2Q==', N'1111', N'13010', 3440, N'Phường Xuân Phương, Quận Nam Từ Liêm, TP.Hà Nội')
GO
INSERT [dbo].[employee] ([id], [id_roles], [code], [name], [gender], [birth_date], [address], [phone], [email], [status], [created_at], [update_at], [deleted], [cccd], [image], [password], [wardcode], [districtcode], [fulladdress]) VALUES (N'460b2017-e7f4-482a-9b33-14b10a0a88cc', 1, N'EM04', N'Tuấn', 0, CAST(N'2003-11-11T00:00:00.000' AS DateTime), N'Đại Đồng', N'0973554745', N'tuan@gmail.com           ', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, N'001203029222', N'data:image/jpeg;base64,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', N'4444', N'13010', 3440, N'Phường Xuân Phương, Quận Nam Từ Liêm, TP.Hà Nội')
GO
INSERT [dbo].[employee] ([id], [id_roles], [code], [name], [gender], [birth_date], [address], [phone], [email], [status], [created_at], [update_at], [deleted], [cccd], [image], [password], [wardcode], [districtcode], [fulladdress]) VALUES (N'124376e7-524e-4c72-b136-23a8c4008bef', 1, N'EM05', N'Tú', 0, CAST(N'2003-11-11T00:00:00.000' AS DateTime), N'Đại Đồng', N'0973554745', N'tu@gmail.com             ', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, N'001203029222', N'data:image/jpeg;base64,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', N'5555', N'13010', 3440, N'Phường Xuân Phương, Quận Nam Từ Liêm, TP.Hà Nội')
GO
INSERT [dbo].[employee] ([id], [id_roles], [code], [name], [gender], [birth_date], [address], [phone], [email], [status], [created_at], [update_at], [deleted], [cccd], [image], [password], [wardcode], [districtcode], [fulladdress]) VALUES (N'28e14c18-3485-43a5-ada3-259867821184', 0, N'EM06', N'Thịnh', 0, CAST(N'2003-11-11T00:00:00.000' AS DateTime), N'Hòa Bình', N'0973554745', N'thinh1@gmail.com         ', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, N'001203029222', N'data:image/jpeg;base64,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', N'6666', N'13010', 3440, N'Phường Xuân Phương, Quận Nam Từ Liêm, TP.Hà Nội')
GO
INSERT [dbo].[employee] ([id], [id_roles], [code], [name], [gender], [birth_date], [address], [phone], [email], [status], [created_at], [update_at], [deleted], [cccd], [image], [password], [wardcode], [districtcode], [fulladdress]) VALUES (N'c70a9a5a-b07a-4964-8839-3b41372e22e6', 1, N'EM07', N'Hào', 0, CAST(N'2003-11-11T00:00:00.000' AS DateTime), N'Hòa Bình', N'0973554745', N'hao@gmail.com            ', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, N'001203029222', N'data:image/jpeg;base64,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', N'7777', N'13010', 3440, N'Phường Xuân Phương, Quận Nam Từ Liêm, TP.Hà Nội')
GO
INSERT [dbo].[employee] ([id], [id_roles], [code], [name], [gender], [birth_date], [address], [phone], [email], [status], [created_at], [update_at], [deleted], [cccd], [image], [password], [wardcode], [districtcode], [fulladdress]) VALUES (N'750bc699-8ce0-42a0-a5cc-4ecb28a86d97', 1, N'EM08', N'Kiên', 0, CAST(N'2003-11-11T00:00:00.000' AS DateTime), N'Địch Vĩ', N'0973554745', N'kien@gmail.com           ', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, N'001203029222', N'data:image/jpeg;base64,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', N'8888', N'13010', 3440, N'Phường Xuân Phương, Quận Nam Từ Liêm, TP.Hà Nội')
GO
INSERT [dbo].[employee] ([id], [id_roles], [code], [name], [gender], [birth_date], [address], [phone], [email], [status], [created_at], [update_at], [deleted], [cccd], [image], [password], [wardcode], [districtcode], [fulladdress]) VALUES (N'32b8bc44-e1a4-4547-9c9f-5ebf9bd74bcf', 0, N'EM09', N'Tuyền', 1, CAST(N'2003-11-11T00:00:00.000' AS DateTime), N'Địch Vĩ', N'0973554745', N'tuyen@gmail.com          ', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, N'001203029222', N'data:image/jpeg;base64,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', N'9999', N'13010', 3440, N'Phường Xuân Phương, Quận Nam Từ Liêm, TP.Hà Nội')
GO
INSERT [dbo].[employee] ([id], [id_roles], [code], [name], [gender], [birth_date], [address], [phone], [email], [status], [created_at], [update_at], [deleted], [cccd], [image], [password], [wardcode], [districtcode], [fulladdress]) VALUES (N'5f2d6629-9a69-41dd-ad27-6cc5bc8693ee', 1, N'EM10', N'Huệ', 1, CAST(N'2003-11-11T00:00:00.000' AS DateTime), N'Ba Đình', N'0973554745', N'hue@gmail.com            ', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, N'001203029222', N'data:image/jpeg;base64,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', N'1112', N'13010', 3440, N'Phường Xuân Phương, Quận Nam Từ Liêm, TP.Hà Nội')
GO
SET IDENTITY_INSERT [dbo].[evaluate] ON 
GO
INSERT [dbo].[evaluate] ([id], [id_product_detail], [id_customer], [description], [start_time], [status], [created_at], [update_at], [deleted]) VALUES (0, 0, N'64ac93b6-a093-4f7a-84ff-050e5773385a', N'Khăn đẹp, đúng như trên mẫu mã, sẽ ủng hộ thêm', CAST(N'2023-11-10T00:00:00.000' AS DateTime), 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[evaluate] ([id], [id_product_detail], [id_customer], [description], [start_time], [status], [created_at], [update_at], [deleted]) VALUES (1, 1, N'79a3a3c5-4e10-46a3-855a-080aee7fb16d', N'Vừa với giá tiền, giao hàng rất nhanh chưa đầy 2 hôm đã có ', CAST(N'2023-11-10T00:00:00.000' AS DateTime), 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[evaluate] ([id], [id_product_detail], [id_customer], [description], [start_time], [status], [created_at], [update_at], [deleted]) VALUES (2, 2, N'c6626b2d-d721-4285-bf06-51930e7d2ac3', N'Hợp túi tiền, rất đẹp ủng hộ shop', CAST(N'2023-11-10T00:00:00.000' AS DateTime), 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[evaluate] ([id], [id_product_detail], [id_customer], [description], [start_time], [status], [created_at], [update_at], [deleted]) VALUES (3, 13, N'99903190-37a9-48fd-b8e0-1db1779736fe', N'Lần đầu mua khăn mà đẹp hơn tưởng tượng, 5 sao cho sốp', CAST(N'2023-11-10T00:00:00.000' AS DateTime), 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[evaluate] ([id], [id_product_detail], [id_customer], [description], [start_time], [status], [created_at], [update_at], [deleted]) VALUES (4, 16, N'5e95507c-932f-4a64-90f0-3308f66e2edc', N'Để mà nói thì cũng bình thường, nhưng vì mấy anh code web đẹp trai nên 5 sao', CAST(N'2023-11-10T00:00:00.000' AS DateTime), 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[evaluate] ([id], [id_product_detail], [id_customer], [description], [start_time], [status], [created_at], [update_at], [deleted]) VALUES (5, 20, N'ca69225b-b192-41f9-a056-384f94970ea0', N'Rất tuyệt vời và uy tín 10 điểm', CAST(N'2023-11-10T00:00:00.000' AS DateTime), 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[evaluate] ([id], [id_product_detail], [id_customer], [description], [start_time], [status], [created_at], [update_at], [deleted]) VALUES (6, 22, N'7e073e67-0c6b-4010-8ae0-c9688218857e', N'Lần đầu mua khăn mà đẹp hơn tưởng tượng, 5 sao cho sốp', CAST(N'2023-11-10T00:00:00.000' AS DateTime), 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[evaluate] ([id], [id_product_detail], [id_customer], [description], [start_time], [status], [created_at], [update_at], [deleted]) VALUES (7, 1, N'3026a5bd-e215-4edb-8a32-db71c1a7d750', N'Khăn đẹp, đúng như trên mẫu mã, sẽ ủng hộ thêm', CAST(N'2023-11-10T00:00:00.000' AS DateTime), 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[evaluate] ([id], [id_product_detail], [id_customer], [description], [start_time], [status], [created_at], [update_at], [deleted]) VALUES (8, 26, N'03bef975-baa5-43ad-ab1f-e4826eb049d6', N'Hợp túi tiền, rất đẹp ủng hộ shop', CAST(N'2023-11-10T00:00:00.000' AS DateTime), 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[evaluate] ([id], [id_product_detail], [id_customer], [description], [start_time], [status], [created_at], [update_at], [deleted]) VALUES (9, 27, N'30a995ca-109b-4254-8fa0-ec50d675de18', N'Để mà nói thì cũng bình thường, nhưng vì mấy anh code web đẹp trai nên 5 sao', CAST(N'2023-11-10T00:00:00.000' AS DateTime), 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[evaluate] ([id], [id_product_detail], [id_customer], [description], [start_time], [status], [created_at], [update_at], [deleted]) VALUES (10, 15, N'40162844-7d69-4736-b520-3a8d546bd176', N'Khăn đẹp, đúng như trên mẫu mã, sẽ ủng hộ thêm', CAST(N'2023-11-10T00:00:00.000' AS DateTime), 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
SET IDENTITY_INSERT [dbo].[evaluate] OFF
GO
SET IDENTITY_INSERT [dbo].[image] ON 
GO
INSERT [dbo].[image] ([id], [id_product_detail], [image], [discription], [status], [created_at], [update_at], [deleted]) VALUES (0, 0, N'https://res.cloudinary.com/da30qcqmf/image/upload/v1699553783/DUANTOTNGHIEP/7281D9D0-ECA6-4028-A214-393456E05C22_dr5sue.png', N'ảnh màu 1 kiểu 1', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[image] ([id], [id_product_detail], [image], [discription], [status], [created_at], [update_at], [deleted]) VALUES (1, 0, N'https://res.cloudinary.com/da30qcqmf/image/upload/v1699553783/DUANTOTNGHIEP/79B253B8-91E5-483A-A320-98486D7559A1_ntdrez.png', N'ảnh màu 1 kiểu 2', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[image] ([id], [id_product_detail], [image], [discription], [status], [created_at], [update_at], [deleted]) VALUES (2, 0, N'https://res.cloudinary.com/da30qcqmf/image/upload/v1699553782/DUANTOTNGHIEP/374D9CF5-B398-4855-9160-5B10D8469B5B_kllnzv.png', N'ảnh màu 1 kiểu 3', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[image] ([id], [id_product_detail], [image], [discription], [status], [created_at], [update_at], [deleted]) VALUES (3, 0, N'https://res.cloudinary.com/da30qcqmf/image/upload/v1699553783/DUANTOTNGHIEP/A32A6AB6-C5D5-4B7E-8120-DBACD99B5B51_lroupl.png', N'ảnh màu 1 kiểu 4', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[image] ([id], [id_product_detail], [image], [discription], [status], [created_at], [update_at], [deleted]) VALUES (4, 0, N'https://res.cloudinary.com/da30qcqmf/image/upload/v1699553783/DUANTOTNGHIEP/A2ED6A4B-7975-4929-A626-B5C5707478EE_vfxcm6.png', N'ảnh màu 2 kiểu 1', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[image] ([id], [id_product_detail], [image], [discription], [status], [created_at], [update_at], [deleted]) VALUES (5, 0, N'https://res.cloudinary.com/da30qcqmf/image/upload/v1699553783/DUANTOTNGHIEP/FEA0162A-A9AA-49F4-99B4-122EBE0EAEBB_o7hgck.png', N'ảnh màu 2 kiểu 2', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[image] ([id], [id_product_detail], [image], [discription], [status], [created_at], [update_at], [deleted]) VALUES (6, 0, N'https://res.cloudinary.com/da30qcqmf/image/upload/v1699553783/DUANTOTNGHIEP/E9FAEA44-FF91-437B-84EA-50CE00DBAFCF_nbubcq.png', N'ảnh màu 2 kiểu 3', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[image] ([id], [id_product_detail], [image], [discription], [status], [created_at], [update_at], [deleted]) VALUES (7, 0, N'https://res.cloudinary.com/da30qcqmf/image/upload/v1699553782/DUANTOTNGHIEP/98899294-5E84-4E3A-9BD5-1975E6C6D5E0_x0dbpb.png', N'ảnh màu 2 kiểu 4', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[image] ([id], [id_product_detail], [image], [discription], [status], [created_at], [update_at], [deleted]) VALUES (8, 1, N'https://res.cloudinary.com/da30qcqmf/image/upload/v1699554130/DUANTOTNGHIEP/5e31a335b42fae0d371c2220be0373cf_rbowby.jpg', N'ảnh màu 1 kiểu 1', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[image] ([id], [id_product_detail], [image], [discription], [status], [created_at], [update_at], [deleted]) VALUES (9, 1, N'https://res.cloudinary.com/da30qcqmf/image/upload/v1699554130/DUANTOTNGHIEP/5e31a335b42fae0d37ss1c2220be0373_os2nel.jpg', N'ảnh màu 1 kiểu 2', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[image] ([id], [id_product_detail], [image], [discription], [status], [created_at], [update_at], [deleted]) VALUES (10, 1, N'https://res.cloudinary.com/da30qcqmf/image/upload/v1699554130/DUANTOTNGHIEP/5e31a335b42fae0d32c2220be0373cf_peg2pl.jpg', N'ảnh màu 1 kiểu 3', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[image] ([id], [id_product_detail], [image], [discription], [status], [created_at], [update_at], [deleted]) VALUES (11, 1, N'https://res.cloudinary.com/da30qcqmf/image/upload/v1699554130/DUANTOTNGHIEP/5e31a335b42fae0d371c22210be0373cf_ndblty.jpg', N'ảnh màu 1 kiểu 4', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
SET IDENTITY_INSERT [dbo].[image] OFF
GO
SET IDENTITY_INSERT [dbo].[material] ON 
GO
INSERT [dbo].[material] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (0, N'ML01                     ', N'Thổ Cẩm', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, NULL)
GO
INSERT [dbo].[material] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (1, N'ML02                     ', N'Lụa', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, NULL)
GO
INSERT [dbo].[material] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (2, N'ML03                     ', N'Nhung', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, NULL)
GO
INSERT [dbo].[material] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (3, N'ML04                     ', N'Lông Cừu', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, NULL)
GO
INSERT [dbo].[material] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (4, N'ML05                     ', N'Cotton', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, NULL)
GO
SET IDENTITY_INSERT [dbo].[material] OFF
GO
SET IDENTITY_INSERT [dbo].[origin] ON 
GO
INSERT [dbo].[origin] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (0, N'ON01', N'Trung Quốc', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, 0)
GO
INSERT [dbo].[origin] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (1, N'ON02', N'Hàn Quốc', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, 0)
GO
INSERT [dbo].[origin] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (2, N'ON03', N'Anh Quốc', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, 0)
GO
INSERT [dbo].[origin] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (3, N'ON04', N'Ấn Độ', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, 0)
GO
INSERT [dbo].[origin] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (4, N'ON05', N'Pháp', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, 0)
GO
SET IDENTITY_INSERT [dbo].[origin] OFF
GO
SET IDENTITY_INSERT [dbo].[pattern] ON 
GO
INSERT [dbo].[pattern] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (0, N'PN01', N'Trơn', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, 0)
GO
INSERT [dbo].[pattern] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (1, N'PN02', N'In hoa', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, 0)
GO
INSERT [dbo].[pattern] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (2, N'PN03', N'Kẻ sọc', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, 0)
GO
INSERT [dbo].[pattern] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (3, N'PN04', N'Hình khối', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, 0)
GO
INSERT [dbo].[pattern] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (4, N'PN05', N'Thổ cẩm', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, 0)
GO
SET IDENTITY_INSERT [dbo].[pattern] OFF
GO
SET IDENTITY_INSERT [dbo].[pay] ON 
GO
INSERT [dbo].[pay] ([id], [payment_method], [status], [created_at], [update_at], [deleted]) VALUES (1, N'Tiền mặt', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[pay] ([id], [payment_method], [status], [created_at], [update_at], [deleted]) VALUES (2, N'Chuyển khoản', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
SET IDENTITY_INSERT [dbo].[pay] OFF
GO
SET IDENTITY_INSERT [dbo].[product] ON 
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (0, N'PD01', N'Khăn quàng cổ Skinny ', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (1, N'PD02', N'Khăn quàng cổ Mohair', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (2, N'PD03', N'Khăn quàng cổ cashmere kẻ sọc rộng', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (3, N'PD04', N'Khăn quàng cổ trơn', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (4, N'PD05', N'Khăn quàng cổ trơn', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (5, N'PD06', N'Khăn quàng cổ Cashmere đảo ngược', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (6, N'PD07', N'Khăn quàng cổ trơn nam nữ Chulio, dày dặn', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (7, N'PD08', N'Khăn quàng cổ trơn', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (8, N'PD09', N'Khăn quàng cổ thổ cẩm len dệt kim', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (9, N'PD10', N'Khăn quàng cổ Mohair', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (10, N'PD11', N'Khăn quàng cổ loại mỏng mát vắt vai', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (11, N'PD12', N'Khăn quàng cổ Skinny ', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (12, N'PD13', N'Khăn quàng cổ Nữ Len Lông Cừu Sky Cashmere cao cấp', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (13, N'PD14', N'Khăn quàng cổ Cashmere đảo ngược', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (14, N'PD15', N'Khăn quàng cổ, Khăn choàng lụa cho nữ bản rộng', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (15, N'PD16', N'Khăn quàng cổ trơn', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (16, N'PD17', N'Khăn quàng cổ lụa dài 180x90- Khăn choàng satin', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (17, N'PD18', N'Khăn quàng cổ Mohair', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (18, N'PD19', N'Khăn quàng cổ Thổ Cẩm Đi Biển Khăn Choàng Cổ ', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (19, N'PD20', N'Khăn quàng cổ cashmere kẻ sọc rộng', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (20, N'PD21', N'Khăn quàng cổ Đội đầu Lụa Vuông 90x90 cm Bandana', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[product] ([id], [code], [name], [status], [created_at], [updated_at], [deleted]) VALUES (21, N'PD22', N'Khăn quàng cổ Đội đầu Lụa Vuông 90x90 cm Bandana', 1, NULL, NULL, 0)
GO
SET IDENTITY_INSERT [dbo].[product] OFF
GO
SET IDENTITY_INSERT [dbo].[product_detail] ON 
GO
INSERT [dbo].[product_detail] ([id], [code], [name], [price], [quantity], [description], [id_pattern], [id_color], [id_origin], [id_brand], [id_material], [id_size], [id_styles], [status], [created_at], [update_at], [deleted], [id_product]) VALUES (0, N'PDD01', N'Khăn quàng cổ Skinny ', CAST(200000.000 AS Decimal(20, 3)), 200, N'NOTE:  MÀU SẮC CÓ THỂ THAY ĐỔI 1 CHÚT DO ÁNH SÁNG KHI CHỤP, ĐỘ HIỂN THỊ MÀN HÌNH VÀ TÙY TỪNG CÂY VẢI MỖI ĐỢT HÀNG VỀ!_x000D_', 1, 1, 1, 1, 1, 1, 1, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 0)
GO
INSERT [dbo].[product_detail] ([id], [code], [name], [price], [quantity], [description], [id_pattern], [id_color], [id_origin], [id_brand], [id_material], [id_size], [id_styles], [status], [created_at], [update_at], [deleted], [id_product]) VALUES (1, N'PDD02', N'Khăn quàng cổ Skinny ', CAST(200000.000 AS Decimal(20, 3)), 200, N'NOTE:  MÀU SẮC CÓ THỂ THAY ĐỔI 1 CHÚT DO ÁNH SÁNG KHI CHỤP, ĐỘ HIỂN THỊ MÀN HÌNH VÀ TÙY TỪNG CÂY VẢI MỖI ĐỢT HÀNG VỀ!_x000D_', 2, 2, 2, 1, 2, 2, 2, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 0)
GO
INSERT [dbo].[product_detail] ([id], [code], [name], [price], [quantity], [description], [id_pattern], [id_color], [id_origin], [id_brand], [id_material], [id_size], [id_styles], [status], [created_at], [update_at], [deleted], [id_product]) VALUES (2, N'PDD03', N'Khăn quàng cổ Skinny ', CAST(200000.000 AS Decimal(20, 3)), 200, N'NOTE:  MÀU SẮC CÓ THỂ THAY ĐỔI 1 CHÚT DO ÁNH SÁNG KHI CHỤP, ĐỘ HIỂN THỊ MÀN HÌNH VÀ TÙY TỪNG CÂY VẢI MỖI ĐỢT HÀNG VỀ!_x000D_', 3, 3, 3, 1, 3, 3, 3, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 0)
GO
INSERT [dbo].[product_detail] ([id], [code], [name], [price], [quantity], [description], [id_pattern], [id_color], [id_origin], [id_brand], [id_material], [id_size], [id_styles], [status], [created_at], [update_at], [deleted], [id_product]) VALUES (13, N'PDD04', N'Khăn quàng cổ Skinny ', CAST(200000.000 AS Decimal(20, 3)), 200, N'NOTE:  MÀU SẮC CÓ THỂ THAY ĐỔI 1 CHÚT DO ÁNH SÁNG KHI CHỤP, ĐỘ HIỂN THỊ MÀN HÌNH VÀ TÙY TỪNG CÂY VẢI MỖI ĐỢT HÀNG VỀ!_x000D_', 4, 4, 4, 1, 4, 4, 4, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 0)
GO
INSERT [dbo].[product_detail] ([id], [code], [name], [price], [quantity], [description], [id_pattern], [id_color], [id_origin], [id_brand], [id_material], [id_size], [id_styles], [status], [created_at], [update_at], [deleted], [id_product]) VALUES (14, N'PDD05', N'Khăn quàng cổ Mohair', CAST(250000.000 AS Decimal(20, 3)), 200, N'NOTE:  MÀU SẮC CÓ THỂ THAY ĐỔI 1 CHÚT DO ÁNH SÁNG KHI CHỤP, ĐỘ HIỂN THỊ MÀN HÌNH VÀ TÙY TỪNG CÂY VẢI MỖI ĐỢT HÀNG VỀ!_x000D_', 1, 1, 1, 2, 1, 1, 1, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 1)
GO
INSERT [dbo].[product_detail] ([id], [code], [name], [price], [quantity], [description], [id_pattern], [id_color], [id_origin], [id_brand], [id_material], [id_size], [id_styles], [status], [created_at], [update_at], [deleted], [id_product]) VALUES (15, N'PDD06', N'Khăn quàng cổ Mohair', CAST(250000.000 AS Decimal(20, 3)), 200, N'NOTE:  MÀU SẮC CÓ THỂ THAY ĐỔI 1 CHÚT DO ÁNH SÁNG KHI CHỤP, ĐỘ HIỂN THỊ MÀN HÌNH VÀ TÙY TỪNG CÂY VẢI MỖI ĐỢT HÀNG VỀ!_x000D_', 2, 2, 2, 2, 2, 2, 2, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 1)
GO
INSERT [dbo].[product_detail] ([id], [code], [name], [price], [quantity], [description], [id_pattern], [id_color], [id_origin], [id_brand], [id_material], [id_size], [id_styles], [status], [created_at], [update_at], [deleted], [id_product]) VALUES (16, N'PDD07', N'Khăn quàng cổ Mohair', CAST(250000.000 AS Decimal(20, 3)), 200, N'NOTE:  MÀU SẮC CÓ THỂ THAY ĐỔI 1 CHÚT DO ÁNH SÁNG KHI CHỤP, ĐỘ HIỂN THỊ MÀN HÌNH VÀ TÙY TỪNG CÂY VẢI MỖI ĐỢT HÀNG VỀ!_x000D_', 3, 3, 3, 2, 3, 3, 3, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 1)
GO
INSERT [dbo].[product_detail] ([id], [code], [name], [price], [quantity], [description], [id_pattern], [id_color], [id_origin], [id_brand], [id_material], [id_size], [id_styles], [status], [created_at], [update_at], [deleted], [id_product]) VALUES (17, N'PDD08', N'Khăn quàng cổ Mohair', CAST(250000.000 AS Decimal(20, 3)), 200, N'NOTE:  MÀU SẮC CÓ THỂ THAY ĐỔI 1 CHÚT DO ÁNH SÁNG KHI CHỤP, ĐỘ HIỂN THỊ MÀN HÌNH VÀ TÙY TỪNG CÂY VẢI MỖI ĐỢT HÀNG VỀ!_x000D_', 4, 4, 4, 2, 4, 4, 4, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 1)
GO
INSERT [dbo].[product_detail] ([id], [code], [name], [price], [quantity], [description], [id_pattern], [id_color], [id_origin], [id_brand], [id_material], [id_size], [id_styles], [status], [created_at], [update_at], [deleted], [id_product]) VALUES (18, N'PDD09', N'Khăn quàng cổ cashmere kẻ sọc rộng', CAST(120000.000 AS Decimal(20, 3)), 200, N'NOTE:  MÀU SẮC CÓ THỂ THAY ĐỔI 1 CHÚT DO ÁNH SÁNG KHI CHỤP, ĐỘ HIỂN THỊ MÀN HÌNH VÀ TÙY TỪNG CÂY VẢI MỖI ĐỢT HÀNG VỀ!_x000D_', 1, 1, 1, 3, 1, 1, 1, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 2)
GO
INSERT [dbo].[product_detail] ([id], [code], [name], [price], [quantity], [description], [id_pattern], [id_color], [id_origin], [id_brand], [id_material], [id_size], [id_styles], [status], [created_at], [update_at], [deleted], [id_product]) VALUES (19, N'PDD10', N'Khăn quàng cổ cashmere kẻ sọc rộng', CAST(120000.000 AS Decimal(20, 3)), 200, N'NOTE:  MÀU SẮC CÓ THỂ THAY ĐỔI 1 CHÚT DO ÁNH SÁNG KHI CHỤP, ĐỘ HIỂN THỊ MÀN HÌNH VÀ TÙY TỪNG CÂY VẢI MỖI ĐỢT HÀNG VỀ!_x000D_', 2, 2, 2, 3, 2, 2, 2, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 2)
GO
INSERT [dbo].[product_detail] ([id], [code], [name], [price], [quantity], [description], [id_pattern], [id_color], [id_origin], [id_brand], [id_material], [id_size], [id_styles], [status], [created_at], [update_at], [deleted], [id_product]) VALUES (20, N'PDD11', N'Khăn quàng cổ cashmere kẻ sọc rộng', CAST(120000.000 AS Decimal(20, 3)), 200, N'NOTE:  MÀU SẮC CÓ THỂ THAY ĐỔI 1 CHÚT DO ÁNH SÁNG KHI CHỤP, ĐỘ HIỂN THỊ MÀN HÌNH VÀ TÙY TỪNG CÂY VẢI MỖI ĐỢT HÀNG VỀ!_x000D_', 3, 3, 3, 3, 3, 3, 3, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 2)
GO
INSERT [dbo].[product_detail] ([id], [code], [name], [price], [quantity], [description], [id_pattern], [id_color], [id_origin], [id_brand], [id_material], [id_size], [id_styles], [status], [created_at], [update_at], [deleted], [id_product]) VALUES (21, N'PDD12', N'Khăn quàng cổ cashmere kẻ sọc rộng', CAST(120000.000 AS Decimal(20, 3)), 200, N'NOTE:  MÀU SẮC CÓ THỂ THAY ĐỔI 1 CHÚT DO ÁNH SÁNG KHI CHỤP, ĐỘ HIỂN THỊ MÀN HÌNH VÀ TÙY TỪNG CÂY VẢI MỖI ĐỢT HÀNG VỀ!_x000D_', 4, 4, 4, 3, 4, 4, 4, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 2)
GO
INSERT [dbo].[product_detail] ([id], [code], [name], [price], [quantity], [description], [id_pattern], [id_color], [id_origin], [id_brand], [id_material], [id_size], [id_styles], [status], [created_at], [update_at], [deleted], [id_product]) VALUES (22, N'PDD13', N'Khăn quàng cổ trơn', CAST(270000.000 AS Decimal(20, 3)), 200, N'NOTE:  MÀU SẮC CÓ THỂ THAY ĐỔI 1 CHÚT DO ÁNH SÁNG KHI CHỤP, ĐỘ HIỂN THỊ MÀN HÌNH VÀ TÙY TỪNG CÂY VẢI MỖI ĐỢT HÀNG VỀ!_x000D_', 1, 1, 1, 4, 1, 1, 1, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 3)
GO
INSERT [dbo].[product_detail] ([id], [code], [name], [price], [quantity], [description], [id_pattern], [id_color], [id_origin], [id_brand], [id_material], [id_size], [id_styles], [status], [created_at], [update_at], [deleted], [id_product]) VALUES (23, N'PDD14', N'Khăn quàng cổ trơn', CAST(270000.000 AS Decimal(20, 3)), 200, N'NOTE:  MÀU SẮC CÓ THỂ THAY ĐỔI 1 CHÚT DO ÁNH SÁNG KHI CHỤP, ĐỘ HIỂN THỊ MÀN HÌNH VÀ TÙY TỪNG CÂY VẢI MỖI ĐỢT HÀNG VỀ!_x000D_', 2, 2, 2, 4, 2, 2, 2, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 3)
GO
INSERT [dbo].[product_detail] ([id], [code], [name], [price], [quantity], [description], [id_pattern], [id_color], [id_origin], [id_brand], [id_material], [id_size], [id_styles], [status], [created_at], [update_at], [deleted], [id_product]) VALUES (24, N'PDD15', N'Khăn quàng cổ trơn', CAST(270000.000 AS Decimal(20, 3)), 200, N'NOTE:  MÀU SẮC CÓ THỂ THAY ĐỔI 1 CHÚT DO ÁNH SÁNG KHI CHỤP, ĐỘ HIỂN THỊ MÀN HÌNH VÀ TÙY TỪNG CÂY VẢI MỖI ĐỢT HÀNG VỀ!_x000D_', 3, 3, 3, 4, 3, 3, 3, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 3)
GO
INSERT [dbo].[product_detail] ([id], [code], [name], [price], [quantity], [description], [id_pattern], [id_color], [id_origin], [id_brand], [id_material], [id_size], [id_styles], [status], [created_at], [update_at], [deleted], [id_product]) VALUES (25, N'PDD16', N'Khăn quàng cổ trơn', CAST(270000.000 AS Decimal(20, 3)), 200, N'NOTE:  MÀU SẮC CÓ THỂ THAY ĐỔI 1 CHÚT DO ÁNH SÁNG KHI CHỤP, ĐỘ HIỂN THỊ MÀN HÌNH VÀ TÙY TỪNG CÂY VẢI MỖI ĐỢT HÀNG VỀ!_x000D_', 4, 4, 4, 4, 4, 4, 4, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 3)
GO
INSERT [dbo].[product_detail] ([id], [code], [name], [price], [quantity], [description], [id_pattern], [id_color], [id_origin], [id_brand], [id_material], [id_size], [id_styles], [status], [created_at], [update_at], [deleted], [id_product]) VALUES (26, N'PDD17', N'Khăn quàng cổ Cashmere đảo ngược', CAST(300000.000 AS Decimal(20, 3)), 200, N'NOTE:  MÀU SẮC CÓ THỂ THAY ĐỔI 1 CHÚT DO ÁNH SÁNG KHI CHỤP, ĐỘ HIỂN THỊ MÀN HÌNH VÀ TÙY TỪNG CÂY VẢI MỖI ĐỢT HÀNG VỀ!_x000D_', 1, 1, 1, 1, 1, 1, 1, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 5)
GO
INSERT [dbo].[product_detail] ([id], [code], [name], [price], [quantity], [description], [id_pattern], [id_color], [id_origin], [id_brand], [id_material], [id_size], [id_styles], [status], [created_at], [update_at], [deleted], [id_product]) VALUES (27, N'PDD18', N'Khăn quàng cổ Cashmere đảo ngược', CAST(300000.000 AS Decimal(20, 3)), 200, N'NOTE:  MÀU SẮC CÓ THỂ THAY ĐỔI 1 CHÚT DO ÁNH SÁNG KHI CHỤP, ĐỘ HIỂN THỊ MÀN HÌNH VÀ TÙY TỪNG CÂY VẢI MỖI ĐỢT HÀNG VỀ!_x000D_', 2, 2, 2, 1, 2, 2, 2, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 5)
GO
INSERT [dbo].[product_detail] ([id], [code], [name], [price], [quantity], [description], [id_pattern], [id_color], [id_origin], [id_brand], [id_material], [id_size], [id_styles], [status], [created_at], [update_at], [deleted], [id_product]) VALUES (28, N'PDD19', N'Khăn quàng cổ Cashmere đảo ngược', CAST(300000.000 AS Decimal(20, 3)), 200, N'NOTE:  MÀU SẮC CÓ THỂ THAY ĐỔI 1 CHÚT DO ÁNH SÁNG KHI CHỤP, ĐỘ HIỂN THỊ MÀN HÌNH VÀ TÙY TỪNG CÂY VẢI MỖI ĐỢT HÀNG VỀ!_x000D_', 3, 3, 3, 1, 3, 3, 3, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 5)
GO
INSERT [dbo].[product_detail] ([id], [code], [name], [price], [quantity], [description], [id_pattern], [id_color], [id_origin], [id_brand], [id_material], [id_size], [id_styles], [status], [created_at], [update_at], [deleted], [id_product]) VALUES (29, N'PDD20', N'Khăn quàng cổ Cashmere đảo ngược', CAST(300000.000 AS Decimal(20, 3)), 200, N'NOTE:  MÀU SẮC CÓ THỂ THAY ĐỔI 1 CHÚT DO ÁNH SÁNG KHI CHỤP, ĐỘ HIỂN THỊ MÀN HÌNH VÀ TÙY TỪNG CÂY VẢI MỖI ĐỢT HÀNG VỀ!_x000D_', 4, 4, 4, 1, 4, 4, 4, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 5)
GO
SET IDENTITY_INSERT [dbo].[product_detail] OFF
GO
SET IDENTITY_INSERT [dbo].[roles] ON 
GO
INSERT [dbo].[roles] ([id], [name], [status], [created_at], [update_at], [deleted], [role]) VALUES (0, N'R01', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, N'Quản Lý')
GO
INSERT [dbo].[roles] ([id], [name], [status], [created_at], [update_at], [deleted], [role]) VALUES (1, N'R02', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, N'Nhân Viên')
GO
INSERT [dbo].[roles] ([id], [name], [status], [created_at], [update_at], [deleted], [role]) VALUES (2, N'R02', 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, N'Khách Hàng')
GO
SET IDENTITY_INSERT [dbo].[roles] OFF
GO
SET IDENTITY_INSERT [dbo].[size] ON 
GO
INSERT [dbo].[size] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (0, N'SZ01', N'90*180cm', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, 0)
GO
INSERT [dbo].[size] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (1, N'SZ02', N'130*180cm', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, 0)
GO
INSERT [dbo].[size] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (2, N'SZ03', N'72*188cm', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, 0)
GO
INSERT [dbo].[size] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (3, N'SZ04', N'70*70cm', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, 0)
GO
INSERT [dbo].[size] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (4, N'SZ05', N'50*195cm', NULL, NULL, 1, 0)
GO
SET IDENTITY_INSERT [dbo].[size] OFF
GO
SET IDENTITY_INSERT [dbo].[styles] ON 
GO
INSERT [dbo].[styles] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (0, N'STY01', N'Bohemian', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, 0)
GO
INSERT [dbo].[styles] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (1, N'STY02', N'Chic', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, 0)
GO
INSERT [dbo].[styles] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (2, N'STY03', N'Minimalism', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, 0)
GO
INSERT [dbo].[styles] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (3, N'STY04', N'Natural', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, 0)
GO
INSERT [dbo].[styles] ([id], [code], [name], [created_at], [update_at], [status], [deleted]) VALUES (4, N'STY05', N'Preppy', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, 0)
GO
SET IDENTITY_INSERT [dbo].[styles] OFF
GO
SET IDENTITY_INSERT [dbo].[voucher] ON 
GO
INSERT [dbo].[voucher] ([id], [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount], [min_order]) VALUES (1, N'VC01', N'Giảm giá 10% mặt hàng thời trang', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-12-08T00:00:00.000' AS DateTime), 5, 40000, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, 0, 60000, 400000)
GO
INSERT [dbo].[voucher] ([id], [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount], [min_order]) VALUES (2, N'VC02', N'Giảm giá 5% mặt hàng thời trang', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-12-08T00:00:00.000' AS DateTime), 5, 10000, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, 0, 50000, 200000)
GO
INSERT [dbo].[voucher] ([id], [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount], [min_order]) VALUES (3, N'VC03', N'Giảm giá Freeship', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-12-08T00:00:00.000' AS DateTime), 5, 30000, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 0, 30000, 30000)
GO
INSERT [dbo].[voucher] ([id], [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount], [min_order]) VALUES (4, N'VC04', N'Giảm giá 20% mặt hàng thời trang', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-12-08T00:00:00.000' AS DateTime), 5, 20000, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, 0, 30000, 99000)
GO
INSERT [dbo].[voucher] ([id], [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount], [min_order]) VALUES (5, N'VC05', N'Giảm giá 8% mặt hàng thời trang', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-12-08T00:00:00.000' AS DateTime), 5, 24000, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, 0, 60000, 300000)
GO
INSERT [dbo].[voucher] ([id], [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount], [min_order]) VALUES (6, N'VC06', N'Giảm giá 8% tất cả mặt hàng', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 5, 24000, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, 0, 60000, 300000)
GO
INSERT [dbo].[voucher] ([id], [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount], [min_order]) VALUES (7, N'VC07', N'Giảm giá Freeship ', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 5, 30000, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0, 0, 30000, 30000)
GO
INSERT [dbo].[voucher] ([id], [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount], [min_order]) VALUES (8, N'VC08', N'Giảm giá 20% tất cả mặt hàng', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 5, 20000, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, 0, 30000, 99000)
GO
INSERT [dbo].[voucher] ([id], [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount], [min_order]) VALUES (9, N'VC09', N'Giảm giá 10% tất cả mặt hàng', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 5, 40000, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, 0, 60000, 400000)
GO
INSERT [dbo].[voucher] ([id], [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount], [min_order]) VALUES (10, N'VC10', N'Giảm giá 5% tất cả mặt hàng', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 5, 10000, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, 0, 50000, 200000)
GO
INSERT [dbo].[voucher] ([id], [code], [name], [start_time], [end_time], [quantily], [discount], [status], [created_at], [update_at], [types], [deleted], [max_discount], [min_order]) VALUES (11, N'VC11', N'Giảm giá 5% tất cả mặt hàng', CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 5, 10000, 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 1, 0, 50000, 200000)
GO
SET IDENTITY_INSERT [dbo].[voucher] OFF
GO
SET IDENTITY_INSERT [dbo].[voucher_detail] ON 
GO
INSERT [dbo].[voucher_detail] ([id], [id_bill], [id_voucher], [money_before_reduction], [money_after_reduction], [money_reduction], [status], [created_at], [update_at], [deleted]) VALUES (0, 6, 3, CAST(430000.000 AS Decimal(20, 3)), CAST(400000.000 AS Decimal(20, 3)), CAST(30000.000 AS Decimal(20, 3)), 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[voucher_detail] ([id], [id_bill], [id_voucher], [money_before_reduction], [money_after_reduction], [money_reduction], [status], [created_at], [update_at], [deleted]) VALUES (1, 7, 3, CAST(230000.000 AS Decimal(20, 3)), CAST(200000.000 AS Decimal(20, 3)), CAST(30000.000 AS Decimal(20, 3)), 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[voucher_detail] ([id], [id_bill], [id_voucher], [money_before_reduction], [money_after_reduction], [money_reduction], [status], [created_at], [update_at], [deleted]) VALUES (2, 9, 3, CAST(480000.000 AS Decimal(20, 3)), CAST(450000.000 AS Decimal(20, 3)), CAST(30000.000 AS Decimal(20, 3)), 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[voucher_detail] ([id], [id_bill], [id_voucher], [money_before_reduction], [money_after_reduction], [money_reduction], [status], [created_at], [update_at], [deleted]) VALUES (3, 14, 3, CAST(370000.000 AS Decimal(20, 3)), CAST(340000.000 AS Decimal(20, 3)), CAST(30000.000 AS Decimal(20, 3)), 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
INSERT [dbo].[voucher_detail] ([id], [id_bill], [id_voucher], [money_before_reduction], [money_after_reduction], [money_reduction], [status], [created_at], [update_at], [deleted]) VALUES (5, 15, 3, CAST(860000.000 AS Decimal(20, 3)), CAST(830000.000 AS Decimal(20, 3)), CAST(30000.000 AS Decimal(20, 3)), 1, CAST(N'2023-11-08T00:00:00.000' AS DateTime), CAST(N'2023-11-08T00:00:00.000' AS DateTime), 0)
GO
SET IDENTITY_INSERT [dbo].[voucher_detail] OFF
GO
ALTER TABLE [dbo].[account] ADD  CONSTRAINT [DF_account_id]  DEFAULT (newid()) FOR [id]
GO
ALTER TABLE [dbo].[account] ADD  DEFAULT ((0)) FOR [status]
GO
ALTER TABLE [dbo].[account] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[bill] ADD  DEFAULT ((0)) FOR [status]
GO
ALTER TABLE [dbo].[bill] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[bill_detail] ADD  DEFAULT ((0)) FOR [status]
GO
ALTER TABLE [dbo].[bill_detail] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[brand] ADD  DEFAULT ((0)) FOR [status]
GO
ALTER TABLE [dbo].[brand] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[cart] ADD  DEFAULT ((0)) FOR [status]
GO
ALTER TABLE [dbo].[cart] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[carts_detail] ADD  DEFAULT ((0)) FOR [status]
GO
ALTER TABLE [dbo].[carts_detail] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[color] ADD  DEFAULT ((0)) FOR [status]
GO
ALTER TABLE [dbo].[color] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[customer] ADD  CONSTRAINT [DF_customer_id]  DEFAULT (newid()) FOR [id]
GO
ALTER TABLE [dbo].[customer] ADD  DEFAULT ((0)) FOR [status]
GO
ALTER TABLE [dbo].[customer] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[employee] ADD  CONSTRAINT [DF_staff_id]  DEFAULT (newid()) FOR [id]
GO
ALTER TABLE [dbo].[employee] ADD  DEFAULT ((0)) FOR [status]
GO
ALTER TABLE [dbo].[employee] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[evaluate] ADD  DEFAULT ((0)) FOR [status]
GO
ALTER TABLE [dbo].[evaluate] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[image] ADD  DEFAULT ((0)) FOR [status]
GO
ALTER TABLE [dbo].[image] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[material] ADD  DEFAULT ((0)) FOR [status]
GO
ALTER TABLE [dbo].[material] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[origin] ADD  DEFAULT ((0)) FOR [status]
GO
ALTER TABLE [dbo].[origin] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[pattern] ADD  DEFAULT ((0)) FOR [status]
GO
ALTER TABLE [dbo].[pattern] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[pay] ADD  DEFAULT ((0)) FOR [status]
GO
ALTER TABLE [dbo].[pay] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[product] ADD  DEFAULT ((0)) FOR [status]
GO
ALTER TABLE [dbo].[product] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[product_detail] ADD  DEFAULT ((0)) FOR [status]
GO
ALTER TABLE [dbo].[product_detail] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[roles] ADD  DEFAULT ((0)) FOR [status]
GO
ALTER TABLE [dbo].[roles] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[size] ADD  DEFAULT ((0)) FOR [status]
GO
ALTER TABLE [dbo].[size] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[styles] ADD  DEFAULT ((0)) FOR [status]
GO
ALTER TABLE [dbo].[styles] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[voucher] ADD  DEFAULT ((0)) FOR [status]
GO
ALTER TABLE [dbo].[voucher] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[voucher_detail] ADD  DEFAULT ((0)) FOR [status]
GO
ALTER TABLE [dbo].[voucher_detail] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[account]  WITH CHECK ADD  CONSTRAINT [FK_account_customer] FOREIGN KEY([id_customer])
REFERENCES [dbo].[customer] ([id])
GO
ALTER TABLE [dbo].[account] CHECK CONSTRAINT [FK_account_customer]
GO
ALTER TABLE [dbo].[bill]  WITH CHECK ADD  CONSTRAINT [FK_bill_customer] FOREIGN KEY([id_customer])
REFERENCES [dbo].[customer] ([id])
GO
ALTER TABLE [dbo].[bill] CHECK CONSTRAINT [FK_bill_customer]
GO
ALTER TABLE [dbo].[bill]  WITH CHECK ADD  CONSTRAINT [FK_bill_employee] FOREIGN KEY([id_employee])
REFERENCES [dbo].[employee] ([id])
GO
ALTER TABLE [dbo].[bill] CHECK CONSTRAINT [FK_bill_employee]
GO
ALTER TABLE [dbo].[bill]  WITH CHECK ADD  CONSTRAINT [FK_bill_pay] FOREIGN KEY([id_pay])
REFERENCES [dbo].[pay] ([id])
GO
ALTER TABLE [dbo].[bill] CHECK CONSTRAINT [FK_bill_pay]
GO
ALTER TABLE [dbo].[bill_detail]  WITH CHECK ADD  CONSTRAINT [FK_bill_detail_bill] FOREIGN KEY([id_bill])
REFERENCES [dbo].[bill] ([id])
GO
ALTER TABLE [dbo].[bill_detail] CHECK CONSTRAINT [FK_bill_detail_bill]
GO
ALTER TABLE [dbo].[bill_detail]  WITH CHECK ADD  CONSTRAINT [FK_bill_detail_product_detail] FOREIGN KEY([id_product_detail])
REFERENCES [dbo].[product_detail] ([id])
GO
ALTER TABLE [dbo].[bill_detail] CHECK CONSTRAINT [FK_bill_detail_product_detail]
GO
ALTER TABLE [dbo].[cart]  WITH CHECK ADD  CONSTRAINT [FK_cart_customer] FOREIGN KEY([id_customer])
REFERENCES [dbo].[customer] ([id])
GO
ALTER TABLE [dbo].[cart] CHECK CONSTRAINT [FK_cart_customer]
GO
ALTER TABLE [dbo].[carts_detail]  WITH CHECK ADD  CONSTRAINT [FK_carts_detail_cart] FOREIGN KEY([id_cart])
REFERENCES [dbo].[cart] ([id])
GO
ALTER TABLE [dbo].[carts_detail] CHECK CONSTRAINT [FK_carts_detail_cart]
GO
ALTER TABLE [dbo].[carts_detail]  WITH CHECK ADD  CONSTRAINT [FK_carts_detail_product_detail] FOREIGN KEY([id_product_detail])
REFERENCES [dbo].[product_detail] ([id])
GO
ALTER TABLE [dbo].[carts_detail] CHECK CONSTRAINT [FK_carts_detail_product_detail]
GO
ALTER TABLE [dbo].[employee]  WITH CHECK ADD  CONSTRAINT [FK_employee_roles] FOREIGN KEY([id_roles])
REFERENCES [dbo].[roles] ([id])
GO
ALTER TABLE [dbo].[employee] CHECK CONSTRAINT [FK_employee_roles]
GO
ALTER TABLE [dbo].[evaluate]  WITH CHECK ADD  CONSTRAINT [FK_evaluate_customer] FOREIGN KEY([id_customer])
REFERENCES [dbo].[customer] ([id])
GO
ALTER TABLE [dbo].[evaluate] CHECK CONSTRAINT [FK_evaluate_customer]
GO
ALTER TABLE [dbo].[evaluate]  WITH CHECK ADD  CONSTRAINT [FK_evaluate_product_detail] FOREIGN KEY([id_product_detail])
REFERENCES [dbo].[product_detail] ([id])
GO
ALTER TABLE [dbo].[evaluate] CHECK CONSTRAINT [FK_evaluate_product_detail]
GO
ALTER TABLE [dbo].[image]  WITH CHECK ADD  CONSTRAINT [FK_image_product_detail] FOREIGN KEY([id_product_detail])
REFERENCES [dbo].[product_detail] ([id])
GO
ALTER TABLE [dbo].[image] CHECK CONSTRAINT [FK_image_product_detail]
GO
ALTER TABLE [dbo].[product_detail]  WITH CHECK ADD  CONSTRAINT [FK_product_detail_brand] FOREIGN KEY([id_brand])
REFERENCES [dbo].[brand] ([id])
GO
ALTER TABLE [dbo].[product_detail] CHECK CONSTRAINT [FK_product_detail_brand]
GO
ALTER TABLE [dbo].[product_detail]  WITH CHECK ADD  CONSTRAINT [FK_product_detail_color] FOREIGN KEY([id_color])
REFERENCES [dbo].[color] ([id])
GO
ALTER TABLE [dbo].[product_detail] CHECK CONSTRAINT [FK_product_detail_color]
GO
ALTER TABLE [dbo].[product_detail]  WITH CHECK ADD  CONSTRAINT [FK_product_detail_material] FOREIGN KEY([id_material])
REFERENCES [dbo].[material] ([id])
GO
ALTER TABLE [dbo].[product_detail] CHECK CONSTRAINT [FK_product_detail_material]
GO
ALTER TABLE [dbo].[product_detail]  WITH CHECK ADD  CONSTRAINT [FK_product_detail_origin] FOREIGN KEY([id_origin])
REFERENCES [dbo].[origin] ([id])
GO
ALTER TABLE [dbo].[product_detail] CHECK CONSTRAINT [FK_product_detail_origin]
GO
ALTER TABLE [dbo].[product_detail]  WITH CHECK ADD  CONSTRAINT [FK_product_detail_pattern] FOREIGN KEY([id_pattern])
REFERENCES [dbo].[pattern] ([id])
GO
ALTER TABLE [dbo].[product_detail] CHECK CONSTRAINT [FK_product_detail_pattern]
GO
ALTER TABLE [dbo].[product_detail]  WITH CHECK ADD  CONSTRAINT [FK_product_detail_product] FOREIGN KEY([id_product])
REFERENCES [dbo].[product] ([id])
GO
ALTER TABLE [dbo].[product_detail] CHECK CONSTRAINT [FK_product_detail_product]
GO
ALTER TABLE [dbo].[product_detail]  WITH CHECK ADD  CONSTRAINT [FK_product_detail_size] FOREIGN KEY([id_size])
REFERENCES [dbo].[size] ([id])
GO
ALTER TABLE [dbo].[product_detail] CHECK CONSTRAINT [FK_product_detail_size]
GO
ALTER TABLE [dbo].[product_detail]  WITH CHECK ADD  CONSTRAINT [FK_product_detail_styles] FOREIGN KEY([id_styles])
REFERENCES [dbo].[styles] ([id])
GO
ALTER TABLE [dbo].[product_detail] CHECK CONSTRAINT [FK_product_detail_styles]
GO
ALTER TABLE [dbo].[voucher_detail]  WITH CHECK ADD  CONSTRAINT [FK_voucher_detail_bill] FOREIGN KEY([id_bill])
REFERENCES [dbo].[bill] ([id])
GO
ALTER TABLE [dbo].[voucher_detail] CHECK CONSTRAINT [FK_voucher_detail_bill]
GO
ALTER TABLE [dbo].[voucher_detail]  WITH CHECK ADD  CONSTRAINT [FK_voucher_detail_voucher] FOREIGN KEY([id_voucher])
REFERENCES [dbo].[voucher] ([id])
GO
ALTER TABLE [dbo].[voucher_detail] CHECK CONSTRAINT [FK_voucher_detail_voucher]
GO
/****** Object:  Trigger [dbo].[trg_generate_bill_code]    Script Date: 11/10/2023 10:43:09 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TRIGGER [dbo].[trg_generate_bill_code]
ON [dbo].[bill]
AFTER INSERT
AS
BEGIN
  DECLARE @code nvarchar(50)
  DECLARE @max_number int
  DECLARE @new_number int

  -- Lấy số mã lớn nhất trong dữ liệu
  SELECT @max_number = MAX(SUBSTRING([code], 2, LEN([code]) - 1))
  FROM [dbo].[bill]
  WHERE [code] LIKE 'B[0-9][0-9]'

  -- Tạo mã mới
  IF @max_number IS NOT NULL
  BEGIN
    SET @new_number = @max_number + 1

    -- Kiểm tra xem số đã tồn tại hay chưa
    WHILE EXISTS (SELECT 1 FROM [dbo].[bill] WHERE [code] = 'B' + RIGHT('0' + CAST(@new_number AS varchar(2)), 2))
    BEGIN
      SET @new_number = @new_number + 1
    END

    SET @code = 'B' + RIGHT('0' + CAST(@new_number AS varchar(2)), 2)
  END
  ELSE
  BEGIN
    SET @code = 'B01'
  END

  -- Cập nhật mã mới vào bản ghi mới được thêm vào
  UPDATE [dbo].[bill]
  SET [code] = @code
  WHERE [code] IS NULL
END

GO
ALTER TABLE [dbo].[bill] ENABLE TRIGGER [trg_generate_bill_code]
GO
/****** Object:  Trigger [dbo].[trg_generate_brand_code]    Script Date: 11/10/2023 10:43:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TRIGGER [dbo].[trg_generate_brand_code]
ON [dbo].[brand]
AFTER INSERT
AS
BEGIN
  DECLARE @code nvarchar(25)
  DECLARE @max_number int
  DECLARE @new_number int

  -- Lấy số mã lớn nhất trong dữ liệu
  SELECT @max_number = MAX(SUBSTRING([code], 3, LEN([code]) - 2))
  FROM [dbo].[brand]
  WHERE [code] LIKE 'BR[0-9][0-9]'

  -- Tạo mã mới
  IF @max_number IS NOT NULL
  BEGIN
    SET @new_number = @max_number + 1

    -- Kiểm tra xem số đã tồn tại hay chưa
    WHILE EXISTS (SELECT 1 FROM [dbo].[brand] WHERE [code] = 'BR' + RIGHT('0' + CAST(@new_number AS varchar(2)), 2))
    BEGIN
      SET @new_number = @new_number + 1
    END

    SET @code = 'BR' + RIGHT('0' + CAST(@new_number AS varchar(2)), 2)
  END
  ELSE
  BEGIN
    SET @code = 'BR01'
  END

  -- Cập nhật mã mới vào bản ghi mới được thêm vào
  UPDATE [dbo].[brand]
  SET [code] = @code
  WHERE [code] IS NULL
END

GO
ALTER TABLE [dbo].[brand] ENABLE TRIGGER [trg_generate_brand_code]
GO
/****** Object:  Trigger [dbo].[trg_generate_cart_code]    Script Date: 11/10/2023 10:43:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TRIGGER [dbo].[trg_generate_cart_code]
ON [dbo].[cart]
INSTEAD OF INSERT
AS
BEGIN
    DECLARE @max_code nvarchar(25);
    DECLARE @new_code nvarchar(25);

    -- Lấy mã code lớn nhất trong bảng
    SELECT TOP 1 @max_code = [code]
    FROM [dbo].[cart]
    ORDER BY [code] DESC;

    -- Nếu không có mã code trong bảng
    IF @max_code IS NULL
    BEGIN
        SET @new_code = 'C01';
    END
    ELSE
    BEGIN
        -- Lấy số trong mã code lớn nhất và tăng lên 1
        DECLARE @max_number int;
        SET @max_number = CAST(SUBSTRING(@max_code, 2, LEN(@max_code)) AS int) + 1;

        -- Format lại số để có đủ số lượng chữ số
        SET @new_code = 'C' + RIGHT('00' + CAST(@max_number AS nvarchar), 2);
    END;

    -- Thêm dữ liệu mới vào bảng với mã code mới
    INSERT INTO [dbo].[cart] ( 
        [code], [id_customer], [start_time], [quantity], [total_money], [status], [created_at], [updated_at], [deleted]
    )
    SELECT 
        @new_code, [id_customer], [start_time], [quantity], [total_money], [status], [created_at], [updated_at], [deleted]
    FROM inserted;
END;

GO
ALTER TABLE [dbo].[cart] ENABLE TRIGGER [trg_generate_cart_code]
GO
/****** Object:  Trigger [dbo].[trg_generate_color_code]    Script Date: 11/10/2023 10:43:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TRIGGER [dbo].[trg_generate_color_code]
ON [dbo].[color]
AFTER INSERT
AS
BEGIN
  DECLARE @code nvarchar(25)
  DECLARE @max_number int
  DECLARE @new_number int

  -- Lấy số mã lớn nhất trong dữ liệu
  SELECT @max_number = MAX(SUBSTRING([code], 3, LEN([code]) - 2))
  FROM [dbo].[color]
  WHERE [code] LIKE 'CL[0-9][0-9]'

  -- Tạo mã mới
  IF @max_number IS NOT NULL
  BEGIN
    SET @new_number = @max_number + 1

    -- Kiểm tra xem số đã tồn tại hay chưa
    WHILE EXISTS (SELECT 1 FROM [dbo].[color] WHERE [code] = 'CL' + RIGHT('0' + CAST(@new_number AS varchar(2)), 2))
    BEGIN
      SET @new_number = @new_number + 1
    END

    SET @code = 'CL' + RIGHT('0' + CAST(@new_number AS varchar(2)), 2)
  END
  ELSE
  BEGIN
    SET @code = 'CL01'
  END

  -- Cập nhật mã mới vào bản ghi mới được thêm vào
  UPDATE [dbo].[color]
  SET [code] = @code
  WHERE [code] IS NULL
END

GO
ALTER TABLE [dbo].[color] ENABLE TRIGGER [trg_generate_color_code]
GO
/****** Object:  Trigger [dbo].[trg_generate_material_code]    Script Date: 11/10/2023 10:43:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TRIGGER [dbo].[trg_generate_material_code]
ON [dbo].[material]
AFTER INSERT
AS
BEGIN
  DECLARE @code nchar(25)
  DECLARE @max_number int
  DECLARE @new_number int

  -- Lấy số mã lớn nhất trong dữ liệu
  SELECT @max_number = MAX(SUBSTRING([code], 3, LEN([code]) - 2))
  FROM [dbo].[material]
  WHERE [code] LIKE 'ML[0-9][0-9]'

  -- Tạo mã mới
  IF @max_number IS NOT NULL
  BEGIN
    SET @new_number = @max_number + 1

    -- Kiểm tra xem số đã tồn tại hay chưa
    WHILE EXISTS (SELECT 1 FROM [dbo].[material] WHERE [code] = 'ML' + RIGHT('0' + CAST(@new_number AS varchar(2)), 2))
    BEGIN
      SET @new_number = @new_number + 1
    END

    SET @code = 'ML' + RIGHT('0' + CAST(@new_number AS varchar(2)), 2)
  END
  ELSE
  BEGIN
    SET @code = 'ML01'
  END

  -- Cập nhật mã mới vào bản ghi mới được thêm vào
  UPDATE [dbo].[material]
  SET [code] = @code
  WHERE [code] IS NULL
END

GO
ALTER TABLE [dbo].[material] ENABLE TRIGGER [trg_generate_material_code]
GO
/****** Object:  Trigger [dbo].[trg_material]    Script Date: 11/10/2023 10:43:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TRIGGER [dbo].[trg_material]
ON [dbo].[material]
FOR INSERT
AS
BEGIN
    DECLARE @code nchar(25)
    DECLARE @newId int

    SELECT @newId = MAX(ID) FROM inserted

    SET @code = 'ML' + RIGHT('00' + CAST(@newId AS nvarchar), 2)

    UPDATE [dbo].[material]
    SET [code] = @code
    WHERE [ID] = @newId
END

GO
ALTER TABLE [dbo].[material] ENABLE TRIGGER [trg_material]
GO
/****** Object:  Trigger [dbo].[trg_generate_origin_code]    Script Date: 11/10/2023 10:43:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TRIGGER [dbo].[trg_generate_origin_code]
ON [dbo].[origin]
AFTER INSERT
AS
BEGIN
  DECLARE @code nvarchar(25)
  DECLARE @max_number int
  DECLARE @new_number int

  -- Lấy số mã lớn nhất trong dữ liệu
  SELECT @max_number = MAX(SUBSTRING([code], 3, LEN([code]) - 2))
  FROM [dbo].[origin]
  WHERE [code] LIKE 'ON[0-9][0-9]'

  -- Tạo mã mới
  IF @max_number IS NOT NULL
  BEGIN
    SET @new_number = @max_number + 1

    -- Kiểm tra xem số đã tồn tại hay chưa
    WHILE EXISTS (SELECT 1 FROM [dbo].[origin] WHERE [code] = 'ON' + RIGHT('0' + CAST(@new_number AS varchar(2)), 2))
    BEGIN
      SET @new_number = @new_number + 1
    END

    SET @code = 'ON' + RIGHT('0' + CAST(@new_number AS varchar(2)), 2)
  END
  ELSE
  BEGIN
    SET @code = 'ON01'
  END

  -- Cập nhật mã mới vào bản ghi mới được thêm vào
  UPDATE [dbo].[origin]
  SET [code] = @code
  WHERE [code] IS NULL
END




GO
ALTER TABLE [dbo].[origin] ENABLE TRIGGER [trg_generate_origin_code]
GO
/****** Object:  Trigger [dbo].[trg_generate_pattern_code]    Script Date: 11/10/2023 10:43:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TRIGGER [dbo].[trg_generate_pattern_code]
ON [dbo].[pattern]
AFTER INSERT
AS
BEGIN
  DECLARE @code nvarchar(25)
  DECLARE @max_number int
  DECLARE @new_number int

  -- Lấy số mã lớn nhất trong dữ liệu
  SELECT @max_number = MAX(SUBSTRING([code], 3, LEN([code]) - 2))
  FROM [dbo].[pattern]
  WHERE [code] LIKE 'PN[0-9][0-9]'

  -- Tạo mã mới
  IF @max_number IS NOT NULL
  BEGIN
    SET @new_number = @max_number + 1

    -- Kiểm tra xem số đã tồn tại hay chưa
    WHILE EXISTS (SELECT 1 FROM [dbo].[pattern] WHERE [code] = 'PN' + RIGHT('0' + CAST(@new_number AS varchar(2)), 2))
    BEGIN
      SET @new_number = @new_number + 1
    END

    SET @code = 'PN' + RIGHT('0' + CAST(@new_number AS varchar(2)), 2)
  END
  ELSE
  BEGIN
    SET @code = 'PN01'
  END

  -- Cập nhật mã mới vào bản ghi mới được thêm vào
  UPDATE [dbo].[pattern]
  SET [code] = @code
  WHERE [code] IS NULL
END



GO
ALTER TABLE [dbo].[pattern] ENABLE TRIGGER [trg_generate_pattern_code]
GO
/****** Object:  Trigger [dbo].[trg_generate_product_code]    Script Date: 11/10/2023 10:43:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TRIGGER [dbo].[trg_generate_product_code]
ON [dbo].[product]
AFTER INSERT
AS
BEGIN
  DECLARE @code nvarchar(25)
  DECLARE @max_number int
  DECLARE @new_number int

  -- Lấy số mã lớn nhất trong dữ liệu
  SELECT @max_number = MAX(SUBSTRING([code], 3, LEN([code]) - 2))
  FROM [dbo].[product]
  WHERE [code] LIKE 'PD[0-9][0-9]'

  -- Tạo mã mới
  IF @max_number IS NOT NULL
  BEGIN
    SET @new_number = @max_number + 1

    -- Kiểm tra xem số đã tồn tại hay chưa
    WHILE EXISTS (SELECT 1 FROM [dbo].[product] WHERE [code] = 'PD' + RIGHT('0' + CAST(@new_number AS varchar(2)), 2))
    BEGIN
      SET @new_number = @new_number + 1
    END

    SET @code = 'PD' + RIGHT('0' + CAST(@new_number AS varchar(2)), 2)
  END
  ELSE
  BEGIN
    SET @code = 'PD01'
  END

  -- Cập nhật mã mới vào bản ghi mới được thêm vào
  UPDATE [dbo].[product]
  SET [code] = @code
  WHERE [code] IS NULL
END

GO
ALTER TABLE [dbo].[product] ENABLE TRIGGER [trg_generate_product_code]
GO
/****** Object:  Trigger [dbo].[trg_generate_productdetail_code]    Script Date: 11/10/2023 10:43:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TRIGGER [dbo].[trg_generate_productdetail_code]
ON [dbo].[product_detail]
INSTEAD OF INSERT
AS
BEGIN
    DECLARE @max_code nvarchar(100)
    DECLARE @new_code nvarchar(100)

    -- Lấy mã code lớn nhất trong bảng
    SELECT TOP 1 @max_code = [code]
    FROM [dbo].[product_detail]
    ORDER BY [code] DESC

    -- Nếu không có mã code trong bảng
    IF @max_code IS NULL
    BEGIN
        SET @new_code = 'PDD01'
    END
    ELSE
    BEGIN
        -- Lấy số trong mã code lớn nhất và tăng lên 1
        DECLARE @max_number int
        SET @max_number = CAST(SUBSTRING(@max_code, 4, LEN(@max_code)) AS int) + 1

        -- Format lại số để có đủ số lượng chữ số
        SET @new_code = 'PDD' + RIGHT('00' + CAST(@max_number AS nvarchar(100)), 2)
    END

    -- Thêm dữ liệu mới vào bảng với mã code mới
    INSERT INTO [dbo].[product_detail] ( 
        [code], [name], [price], [quantity], [description], [id_pattern], [id_color], [id_origin], [id_brand], [id_material], 
        [id_size], [id_styles], [status], [created_at], [update_at], [deleted], [id_product]
    )
    SELECT 
        @new_code, [name], [price], [quantity], [description], [id_pattern], [id_color], [id_origin], [id_brand], [id_material], 
        [id_size], [id_styles], [status], [created_at], [update_at], [deleted], [id_product]
    FROM inserted
END

GO
ALTER TABLE [dbo].[product_detail] ENABLE TRIGGER [trg_generate_productdetail_code]
GO
/****** Object:  Trigger [dbo].[trg_generate_size_code]    Script Date: 11/10/2023 10:43:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TRIGGER [dbo].[trg_generate_size_code]
ON [dbo].[size]
AFTER INSERT
AS
BEGIN
  DECLARE @code nvarchar(25)
  DECLARE @max_number int
  DECLARE @new_number int

  -- Lấy số mã lớn nhất trong dữ liệu
  SELECT @max_number = MAX(SUBSTRING([code], 3, LEN([code]) - 2))
  FROM [dbo].[size]
  WHERE [code] LIKE 'SZ[0-9][0-9]'

  -- Tạo mã mới
  IF @max_number IS NOT NULL
  BEGIN
    SET @new_number = @max_number + 1

    -- Kiểm tra xem số đã tồn tại hay chưa
    WHILE EXISTS (SELECT 1 FROM [dbo].[size] WHERE [code] = 'SZ' + RIGHT('0' + CAST(@new_number AS varchar(2)), 2))
    BEGIN
      SET @new_number = @new_number + 1
    END

    SET @code = 'SZ' + RIGHT('0' + CAST(@new_number AS varchar(2)), 2)
  END
  ELSE
  BEGIN
    SET @code = 'SZ01'
  END

  -- Cập nhật mã mới vào bản ghi mới được thêm vào
  UPDATE [dbo].[size]
  SET [code] = @code
  WHERE [code] IS NULL
END

GO
ALTER TABLE [dbo].[size] ENABLE TRIGGER [trg_generate_size_code]
GO
/****** Object:  Trigger [dbo].[trg_generate_style_code]    Script Date: 11/10/2023 10:43:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TRIGGER [dbo].[trg_generate_style_code]
ON [dbo].[styles]
AFTER INSERT
AS
BEGIN
  DECLARE @code nvarchar(25)
  DECLARE @max_number int
  DECLARE @new_number int

  -- Lấy số mã lớn nhất trong dữ liệu
  SELECT @max_number = MAX(SUBSTRING([code], 4, LEN([code]) - 3))
  FROM [dbo].[styles]
  WHERE [code] LIKE 'STY[0-9][0-9]'

  -- Tạo mã mới
  IF @max_number IS NOT NULL
  BEGIN
    SET @new_number = @max_number + 1

    -- Kiểm tra xem số đã tồn tại hay chưa
    WHILE EXISTS (SELECT 1 FROM [dbo].[styles] WHERE [code] = 'STY' + RIGHT('0' + CAST(@new_number AS varchar(2)), 2))
    BEGIN
      SET @new_number = @new_number + 1
    END

    SET @code = 'STY' + RIGHT('0' + CAST(@new_number AS varchar(2)), 2)
  END
  ELSE
  BEGIN
    SET @code = 'STY01'
  END

  -- Cập nhật mã mới vào bản ghi mới được thêm vào
  UPDATE [dbo].[styles]
  SET [code] = @code
  WHERE [code] IS NULL
END
GO
ALTER TABLE [dbo].[styles] ENABLE TRIGGER [trg_generate_style_code]
GO
/****** Object:  Trigger [dbo].[trg_generate_voucher_code]    Script Date: 11/10/2023 10:43:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- Tạo trigger
CREATE TRIGGER [dbo].[trg_generate_voucher_code]
ON [dbo].[voucher]
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
END
GO
ALTER TABLE [dbo].[voucher] ENABLE TRIGGER [trg_generate_voucher_code]
GO
USE [master]
GO
ALTER DATABASE [DUANTN1] SET  READ_WRITE 
GO
