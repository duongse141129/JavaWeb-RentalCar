Create database Assignment3_dotheduong
go
USE Assignment3_dotheduong
GO
/****** Object:  Table [dbo].[tblCar]    Script Date: 7/3/2021 10:43:57 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblCar](
	[carID] [varchar](30) NOT NULL,
	[carName] [nvarchar](50) NULL,
	[color] [varchar](20) NULL,
	[quantity] [int] NULL,
	[price] [float] NULL,
	[nsx] [date] NULL,
	[rating] [float] NULL,
	[typeID] [varchar](10) NULL,
	[status] [bit] NULL,
	[img] [nvarchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[carID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblDiscount]    Script Date: 7/3/2021 10:43:57 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblDiscount](
	[code] [varchar](10) NOT NULL,
	[phanTram] [float] NULL,
	[expiryDate] [date] NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblRole]    Script Date: 7/3/2021 10:43:57 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblRole](
	[roleID] [varchar](10) NOT NULL,
	[roleName] [nvarchar](50) NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[roleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblTypeCar]    Script Date: 7/3/2021 10:43:57 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblTypeCar](
	[typeID] [varchar](10) NOT NULL,
	[typeName] [nvarchar](30) NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[typeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblUser]    Script Date: 7/3/2021 10:43:57 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblUser](
	[email] [varchar](50) NOT NULL,
	[fullName] [nvarchar](50) NULL,
	[password] [varchar](100) NULL,
	[phone] [varchar](10) NULL,
	[address] [nvarchar](50) NULL,
	[createDate] [date] NULL,
	[roleID] [varchar](10) NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[tblCar] ([carID], [carName], [color], [quantity], [price], [nsx], [rating], [typeID], [status], [img]) VALUES (N'C1', N'lamborghini aventador', N'green', 5, 15000, CAST(N'2021-02-25' AS Date), NULL, N'SC', 1, N'https://lux.mycar.vn/wp-content/uploads/2019/11/anh-bia-aventador.jpg')
INSERT [dbo].[tblCar] ([carID], [carName], [color], [quantity], [price], [nsx], [rating], [typeID], [status], [img]) VALUES (N'C10', N'McLaren 720S', N'grey', 15, 21000, CAST(N'2021-02-23' AS Date), NULL, N'SC', 1, N'https://www.autocar.co.uk/sites/autocar.co.uk/files/styles/body-image/public/mclaren-720s_0.jpg?itok=wZbSZ3FZ')
INSERT [dbo].[tblCar] ([carID], [carName], [color], [quantity], [price], [nsx], [rating], [typeID], [status], [img]) VALUES (N'C11', N'Ferrari SF90 Stradale', N'red', 20, 22000, CAST(N'2021-02-23' AS Date), NULL, N'SC', 1, N'https://www.autocar.co.uk/sites/autocar.co.uk/files/styles/body-image/public/1-ferrari-sf90-stradale-2020-fd-hero-front_0.jpg?itok=SEGd1tLc')
INSERT [dbo].[tblCar] ([carID], [carName], [color], [quantity], [price], [nsx], [rating], [typeID], [status], [img]) VALUES (N'C12', N'Ferrari F8 Tributo', N'blue', 30, 23000, CAST(N'2021-02-23' AS Date), NULL, N'SC', 1, N'https://www.autocar.co.uk/sites/autocar.co.uk/files/styles/body-image/public/ferrari_f8_tributo.jpg?itok=1TG8_Tnx')
INSERT [dbo].[tblCar] ([carID], [carName], [color], [quantity], [price], [nsx], [rating], [typeID], [status], [img]) VALUES (N'C13', N'Lamborghini Huracán Evo', N'red', 15, 28000, CAST(N'2021-02-23' AS Date), NULL, N'SC', 1, N'https://www.autocar.co.uk/sites/autocar.co.uk/files/styles/body-image/public/huracan-evo-.jpg?itok=8Yc8R3_R')
INSERT [dbo].[tblCar] ([carID], [carName], [color], [quantity], [price], [nsx], [rating], [typeID], [status], [img]) VALUES (N'C14', N'Ford GT', N'yellow', 16, 26000, CAST(N'2021-02-23' AS Date), NULL, N'SC', 1, N'https://www.autocar.co.uk/sites/autocar.co.uk/files/styles/body-image/public/ford-gt_1.jpg?itok=RTPBCvpp')
INSERT [dbo].[tblCar] ([carID], [carName], [color], [quantity], [price], [nsx], [rating], [typeID], [status], [img]) VALUES (N'C15', N'Lamborghini Aventador SVJ', N'purple', 12, 27000, CAST(N'2021-03-02' AS Date), NULL, N'SC', 1, N'https://www.autocar.co.uk/sites/autocar.co.uk/files/styles/body-image/public/svj_0.jpg?itok=b6zKFb5A')
INSERT [dbo].[tblCar] ([carID], [carName], [color], [quantity], [price], [nsx], [rating], [typeID], [status], [img]) VALUES (N'C16', N'Noble M600', N'grey', 13, 29000, CAST(N'2021-03-02' AS Date), NULL, N'SC', 1, N'https://www.autocar.co.uk/sites/autocar.co.uk/files/styles/body-image/public/noble-m600.jpg?itok=6zOkFVD2')
INSERT [dbo].[tblCar] ([carID], [carName], [color], [quantity], [price], [nsx], [rating], [typeID], [status], [img]) VALUES (N'C17', N'Aston Martin Vanquish', N'blue', 15, 30000, CAST(N'2021-03-02' AS Date), NULL, N'SC', 1, N'https://www.autocar.co.uk/sites/autocar.co.uk/files/styles/body-image/public/aston_vanquish.jpg?itok=8b0SlqQy')
INSERT [dbo].[tblCar] ([carID], [carName], [color], [quantity], [price], [nsx], [rating], [typeID], [status], [img]) VALUES (N'C18', N'Aston Martin Valhalla', N'white', 20, 31000, CAST(N'2021-03-02' AS Date), NULL, N'SC', 1, N'https://www.autocar.co.uk/sites/autocar.co.uk/files/styles/body-image/public/aston-valhalla.jpg?itok=vEZL3xXj')
INSERT [dbo].[tblCar] ([carID], [carName], [color], [quantity], [price], [nsx], [rating], [typeID], [status], [img]) VALUES (N'C19', N'BMW M1', N'grey', 23, 32000, CAST(N'2021-03-03' AS Date), NULL, N'SC', 1, N'https://www.autocar.co.uk/sites/autocar.co.uk/files/styles/body-image/public/bmw_vision_m.jpg?itok=paQSRlgV')
INSERT [dbo].[tblCar] ([carID], [carName], [color], [quantity], [price], [nsx], [rating], [typeID], [status], [img]) VALUES (N'C2', N'bugatti chiron', N'blue', 3, 20000, CAST(N'2021-02-24' AS Date), NULL, N'SC', 1, N'https://cdn.motor1.com/images/mgl/Xpy6p/s1/bugati-chiron-pur-sport-in-london.jpg')
INSERT [dbo].[tblCar] ([carID], [carName], [color], [quantity], [price], [nsx], [rating], [typeID], [status], [img]) VALUES (N'C20', N'Mazda CX-30', N'black', 24, 8000, CAST(N'2021-03-03' AS Date), NULL, N'SUV', 1, N'https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/2021-mazda-cx-30-2p5-turbo-112-1612840730.jpg?crop=0.633xw:0.474xh;0.185xw,0.383xh&resize=980:*')
INSERT [dbo].[tblCar] ([carID], [carName], [color], [quantity], [price], [nsx], [rating], [typeID], [status], [img]) VALUES (N'C21', N'Hyundai Kona', N'blue', 25, 9000, CAST(N'2021-03-03' AS Date), NULL, N'SUV', 1, N'https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/2021-hyundai-kona-mmp-1-1596819352.jpg?crop=0.743xw:0.556xh;0.0714xw,0.258xh&resize=980:*')
INSERT [dbo].[tblCar] ([carID], [carName], [color], [quantity], [price], [nsx], [rating], [typeID], [status], [img]) VALUES (N'C22', N'Kia Soul', N'red', 30, 5000, CAST(N'2021-03-03' AS Date), NULL, N'SUV', 1, N'https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/2021-kia-soul-mmp-1-1593638236.jpg?crop=0.633xw:0.474xh;0.145xw,0.357xh&resize=980:*')
INSERT [dbo].[tblCar] ([carID], [carName], [color], [quantity], [price], [nsx], [rating], [typeID], [status], [img]) VALUES (N'C23', N' Volvo XC40', N'whilte', 31, 11000, CAST(N'2021-03-03' AS Date), NULL, N'SUV', 1, N'https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/2021-volvo-xc40-mmp-1-1598474101.jpg?crop=0.786xw:0.750xh;0.122xw,0.136xh&resize=980:*')
INSERT [dbo].[tblCar] ([carID], [carName], [color], [quantity], [price], [nsx], [rating], [typeID], [status], [img]) VALUES (N'C24', N'Mazda MX-5 Miata', N'white', 32, 7000, CAST(N'2021-03-03' AS Date), NULL, N'CC', 1, N'https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/2021-mazda-mx-5-miata-mmp-1-1593459650.jpg?crop=0.781xw:0.739xh;0.0952xw,0.113xh&resize=980:*')
INSERT [dbo].[tblCar] ([carID], [carName], [color], [quantity], [price], [nsx], [rating], [typeID], [status], [img]) VALUES (N'C25', N'Porsche 718 Boxster', N'Red', 33, 6000, CAST(N'2021-03-03' AS Date), NULL, N'CC', 1, N'https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/2021-porsche-718-cayman-mmp-1-1593003156.jpg?crop=0.738xw:0.554xh;0.133xw,0.232xh&resize=980:*')
INSERT [dbo].[tblCar] ([carID], [carName], [color], [quantity], [price], [nsx], [rating], [typeID], [status], [img]) VALUES (N'C3', N'bugatti la voiture noire', N'black', 6, 25000, CAST(N'2021-02-21' AS Date), 5.5, N'SC', 1, N'https://lux.mycar.vn/wp-content/uploads/2019/12/Thong-tin-co-ban-ve-Bugatti-La-Voiture-Noire.jpg')
INSERT [dbo].[tblCar] ([carID], [carName], [color], [quantity], [price], [nsx], [rating], [typeID], [status], [img]) VALUES (N'C4', N'ferrari 488', N'red', 8, 13000, CAST(N'2021-02-22' AS Date), 7.5, N'SC', 1, N'https://vnn-imgs-a1.vgcloud.vn/znews-photo.zadn.vn/w1024/Uploaded/lce_cjvcc/2020_03_22/IMG_7171_zing.jpg')
INSERT [dbo].[tblCar] ([carID], [carName], [color], [quantity], [price], [nsx], [rating], [typeID], [status], [img]) VALUES (N'C5', N'

Rolls-Royce Cullinan', N'white', 5, 16000, CAST(N'2021-02-23' AS Date), NULL, N'SUV', 1, N'https://upload.wikimedia.org/wikipedia/commons/thumb/7/79/2019_Rolls-Royce_Cullinan_in_Arctic_White%2C_front_left.jpg/1200px-2019_Rolls-Royce_Cullinan_in_Arctic_White%2C_front_left.jpg')
INSERT [dbo].[tblCar] ([carID], [carName], [color], [quantity], [price], [nsx], [rating], [typeID], [status], [img]) VALUES (N'C6', N'
Lamborghini Urus', N'yellow', 15, 18000, CAST(N'2021-02-25' AS Date), NULL, N'SUV', 1, N'https://lux.mycar.vn/wp-content/uploads/2019/11/anh-bia-urus.jpg')
INSERT [dbo].[tblCar] ([carID], [carName], [color], [quantity], [price], [nsx], [rating], [typeID], [status], [img]) VALUES (N'C7', N'Chevrolet Corvette C8', N'blue', 12, 12000, CAST(N'2021-02-24' AS Date), NULL, N'CC', 1, N'https://image.thanhnien.vn/660/uploaded/chicuong/2020_10_21/corvette/corvette7_xirj.jpg')
INSERT [dbo].[tblCar] ([carID], [carName], [color], [quantity], [price], [nsx], [rating], [typeID], [status], [img]) VALUES (N'C8', N'
BMW i8', N'grey', 30, 10000, CAST(N'2021-02-25' AS Date), NULL, N'CC', 1, N'https://img.tinxe.vn/crop/730x410/2020/06/09/XForF7yt/2020-bmw-i8-2-1560.jpg')
INSERT [dbo].[tblCar] ([carID], [carName], [color], [quantity], [price], [nsx], [rating], [typeID], [status], [img]) VALUES (N'C9', N'Pagani Huayra', N'blue', 28, 24000, CAST(N'2021-02-23' AS Date), NULL, N'CC', 1, N'https://media.vov.vn/uploaded/mgexs9y4vchrwhdtzaag/2019_09_06/622511_nzfe.jpg')
INSERT [dbo].[tblDiscount] ([code], [phanTram], [expiryDate], [status]) VALUES (N'123', 10, CAST(N'2021-03-09' AS Date), 1)
INSERT [dbo].[tblDiscount] ([code], [phanTram], [expiryDate], [status]) VALUES (N'1234', 30, CAST(N'2021-04-06' AS Date), 1)
INSERT [dbo].[tblDiscount] ([code], [phanTram], [expiryDate], [status]) VALUES (N'12345', 20, CAST(N'2021-04-06' AS Date), 1)
INSERT [dbo].[tblDiscount] ([code], [phanTram], [expiryDate], [status]) VALUES (N'55', 40, CAST(N'2021-03-07' AS Date), 1)
INSERT [dbo].[tblRole] ([roleID], [roleName], [status]) VALUES (N'AD', N'Admin', 1)
INSERT [dbo].[tblRole] ([roleID], [roleName], [status]) VALUES (N'US', N'User', 1)
INSERT [dbo].[tblTypeCar] ([typeID], [typeName], [status]) VALUES (N'CC', N'Convertible car', 1)
INSERT [dbo].[tblTypeCar] ([typeID], [typeName], [status]) VALUES (N'SC', N'Supercar', 1)
INSERT [dbo].[tblTypeCar] ([typeID], [typeName], [status]) VALUES (N'SUV', N'Sport Utility Vehicles', 1)
INSERT [dbo].[tblUser] ([email], [fullName], [password], [phone], [address], [createDate], [roleID], [status]) VALUES (N'a@g', N'No name', N'1', N'0258963147', N'23 lê văn việt', CAST(N'2021-02-20' AS Date), N'US', 1)
INSERT [dbo].[tblUser] ([email], [fullName], [password], [phone], [address], [createDate], [roleID], [status]) VALUES (N'admin@gmail', N'Quản lí', N'123', N'0123456987', N'aaaaa', CAST(N'2021-02-20' AS Date), N'AD', 1)
INSERT [dbo].[tblUser] ([email], [fullName], [password], [phone], [address], [createDate], [roleID], [status]) VALUES (N'b@g', N'aaaaaaa', N'123', N'0159357426', N'123asdsad', CAST(N'1900-01-01' AS Date), N'US', 1)
INSERT [dbo].[tblUser] ([email], [fullName], [password], [phone], [address], [createDate], [roleID], [status]) VALUES (N'c@g', N'ccccccc', N'12', N'0159357426', N'123asdsad', CAST(N'2021-02-22' AS Date), N'US', 1)
INSERT [dbo].[tblUser] ([email], [fullName], [password], [phone], [address], [createDate], [roleID], [status]) VALUES (N'd@g', N'ddddd', N'12', N'0147852369', N'123asd', CAST(N'2021-02-22' AS Date), N'US', 1)
INSERT [dbo].[tblUser] ([email], [fullName], [password], [phone], [address], [createDate], [roleID], [status]) VALUES (N'duong@gmail', N'duong', N'123', N'0123456789', N'15 hùng vương', CAST(N'2021-02-21' AS Date), N'US', 0)
INSERT [dbo].[tblUser] ([email], [fullName], [password], [phone], [address], [createDate], [roleID], [status]) VALUES (N'duong97498@gmail.com', N'duongdt', N'123', N'0159357426', N'123asdsad', CAST(N'2021-03-07' AS Date), N'US', 1)
INSERT [dbo].[tblUser] ([email], [fullName], [password], [phone], [address], [createDate], [roleID], [status]) VALUES (N'g@g', N'123', N'1', N'0159357426', N'123asdsad', CAST(N'2021-03-07' AS Date), N'US', 1)
ALTER TABLE [dbo].[tblCar]  WITH CHECK ADD FOREIGN KEY([typeID])
REFERENCES [dbo].[tblTypeCar] ([typeID])
GO
ALTER TABLE [dbo].[tblUser]  WITH CHECK ADD FOREIGN KEY([roleID])
REFERENCES [dbo].[tblRole] ([roleID])
GO
