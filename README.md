# Food Management System

Ứng dụng **Quản lý món ăn** được xây dựng bằng Spring Boot, Thymeleaf, Spring Data JPA và SQL Server cho bài kiểm tra giữa kỳ môn Công nghệ lập trình Web.

**MSSV:** 243233

---

## 1. Công nghệ sử dụng

- Java 21
- Spring Boot 3.5.16
- Spring MVC
- Spring Data JPA
- Hibernate
- Thymeleaf
- Jakarta Validation
- SQL Server
- Maven

---

## 2. Database

Tên database:

```text
243233_MID
```

Các bảng:

```text
243233_DishCategories
243233_Dishes
243233_DishImages
```

Các Entity:

```text
DishCategory_243233
Dish_243233
DishImage_243233
```

Database schema được quản lý bằng Hibernate/JPA Code First.

---

## 3. Chức năng chính

### Quản lý món ăn

- Hiển thị danh sách món ăn
- Xem chi tiết món ăn
- Thêm món ăn
- Chỉnh sửa món ăn
- Chọn danh mục từ dữ liệu trong database
- Kiểm tra dữ liệu nhập
- Không cho phép trùng tên món trong cùng một danh mục
- Hiển thị trạng thái Available / Unavailable

### Phân loại thời gian chuẩn bị

Dựa trên `PreparationTime`:

```text
PreparationTime <= 15 phút → Quick
PreparationTime > 15 phút  → Regular
```

Phân loại này chỉ được tính khi hiển thị và không lưu thành cột riêng trong database.

### Tìm kiếm, lọc và sắp xếp

Hỗ trợ:

- Tìm kiếm gần đúng theo tên món
- Lọc theo danh mục
- Lọc theo trạng thái bán
- Lọc theo khoảng giá
- Sắp xếp giá tăng dần
- Sắp xếp giá giảm dần
- Sắp xếp thời gian chuẩn bị tăng dần
- Kết hợp nhiều điều kiện cùng lúc
- Giữ lại điều kiện tìm kiếm/lọc sau khi submit

Việc lọc dữ liệu được thực hiện ở database query thông qua Spring Data JPA Specification.

### Quản lý hình ảnh

- Thêm hình ảnh bằng URL
- Một món ăn có thể có nhiều hình ảnh
- Hiển thị danh sách hình ảnh trong trang chi tiết
- Chọn và thay đổi thumbnail
- Mỗi món ăn có tối đa một thumbnail

### Quản lý danh mục

- Hiển thị danh sách danh mục
- Không cho phép xóa danh mục đang được món ăn sử dụng

### Đa ngôn ngữ

Ứng dụng hỗ trợ:

- English
- Tiếng Việt

English là ngôn ngữ mặc định.

Ngôn ngữ đã chọn được giữ khi chuyển giữa các trang.

### Xử lý lỗi

Ứng dụng xử lý thân thiện các trường hợp:

- Dish không tồn tại
- Category không tồn tại
- DishImage không tồn tại
- Khoảng giá không hợp lệ
- Không có hình ảnh
- Không có kết quả tìm kiếm
- Dữ liệu Create/Edit không hợp lệ
- Trùng tên món trong cùng danh mục
- Xóa danh mục đang được sử dụng

---

## 4. Cấu hình SQL Server

Tạo database:

```sql
CREATE DATABASE [243233_MID];
```

Ứng dụng sử dụng cấu hình:

```properties
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=243233_MID;encrypt=true;trustServerCertificate=true
spring.datasource.username=sa
spring.datasource.password=${DB_PASSWORD}
```

> Không lưu trực tiếp mật khẩu SQL Server vào source code.

---

## 5. Chạy ứng dụng

### Bước 1: Thiết lập mật khẩu SQL Server

PowerShell:

```powershell
$env:DB_PASSWORD="YOUR_SQL_SERVER_PASSWORD"
```

### Bước 2: Chạy project

```powershell
.\mvnw.cmd spring-boot:run
```

Khi ứng dụng khởi động thành công sẽ xuất hiện:

```text
Tomcat started on port 8080
Started FoodManagementApplication
```

### Bước 3: Truy cập ứng dụng

```text
http://localhost:8080/dishes
```

---

## 6. Cấu trúc chính

```text
src/main/java/com/example/foodmanagement
├── config
├── controller
├── dto
├── model
├── repository
├── service
├── specification
└── FoodManagementApplication.java

src/main/resources
├── templates
│   ├── dishes
│   ├── categories
│   └── errors
├── messages.properties
├── messages_vi.properties
└── application.properties
```

---

## 7. Dữ liệu mẫu

Ứng dụng khởi tạo tối thiểu:

- 3 danh mục
- 5 món ăn

Dữ liệu mẫu được tạo thông qua `DataInitializer`.

---

## 8. Tác giả

**MSSV:** 243233  
**Project:** Food Management System  
**Môn học:** Công nghệ lập trình Web
