insert into category(id,name,description) 
values(10,'electronics','electronics items'),
(11,'stationary',' pen, pencil, a4 sheets');

insert into asset(name,condition_notes,purchase_date,category_id,assignment_status) values 
('pen','cannot be used more','2021-4-5',11,'recovered'),
('keyboard','new','2021-2-5',10,'available');

insert into employee(id,full_name,designation) values 
(101,'Ram Kishan','leader'),
(102,'Shiva','admin'),
(103,'lalu','developer');

insert into asset(name,condition_notes,purchase_date,category_id,assignment_status, employee_id) values 
('laptop','usable','2021-3-5',10,'assigned',102);
