insert into Admin_Staff(PassWrd) 
values ('password1'), ('password2'), ('password3'), ('password4'), ('password5');

insert into Equipment(RecMaintenanceSch, PrevMaintenance, PrevEmployee)
values (4, '2024-04-10', 1), (3, '2024-04-06', 3), (6, '2024-02-10', 2), (2, '2024-04-11', 1);

insert into Time_Slot(TimeSlotDay, StartHr) 
values ('MON', 9), ('MON', 10), ('MON', 11), 
('TUE', 9), ('TUE', 10), ('TUE', 11),
('WED', 9), ('WED', 10), ('WED', 11),
('THU', 9), ('THU', 10), ('THU', 11),
('FRI', 9), ('FRI', 10), ('FRI', 11),
('SAT', 9), ('SAT', 10), ('SAT', 11);

insert into Room (RoomID, IsBooked, TimeSlotID)
values (1, false, 1), (1, false, 2), (1, false, 3), (1, true, 4), (1, false, 5), (1, false, 6),
(2, false, 1), (2, false, 2), (2, false, 3), (2, false, 4), (2, false, 5), (2, false, 6),
(3, true, 1), (3, false, 2), (3, false, 3), (3, false, 4), (3, false, 5), (3, false, 6),
(4, false, 1), (4, false, 2), (4, false, 3), (4, false, 4), (4, false, 5), (4, true, 6),
(5, false, 1), (5, false, 2), (5, false, 3), (5, false, 4), (5, false, 5), (5, false, 6);

insert into Fitness_Class (RoomID, TimeSlotID, ClassName)
values (1, 4, 'Yoga'), (2, 2, 'HIIT'), (3, 1, 'Bootcamp'), (4, 6, 'Boxing');

insert into Member (Name, SkillLevel)
values ('Paul', 17), ('Jamea', 28), ('Pedro', 23), ('John', 25), ('Stefanie', 19), 
('Claudia', 32), ('Katherine', 24), ('Marylin', 22),('Oscar', 35);

insert into Registered (ClassID, MemberID)
values (1,1), (1,2), (1,4), (2,1), (2,3);

insert into Fitness_Goals (MemberID, Name, Completed)
values (1, 'lose 10lbs of fat', false), (1, 'add 3 pounds of muscle', false), (1, 'lose 5% of body fat', false), 
(2, 'lose 10lbs of fat', false), (2, 'add 3 pounds of muscle', true), (2, 'lose 5% of body fat', false), 
(4, 'lose 10lbs of fat', false), (6, 'add 3 pounds of muscle', true), (3, 'lose 5% of body fat', false), 
(7, 'lose 10lbs of fat', false), (5, 'add 3 pounds of muscle', false), (8, 'lose 5% of body fat', true);

insert into Health_Metrics (MemberID, Weight, Steps, BodyFatPercent, Sleep, DayUploaded)
values (1, 124, 10000, 23, 8, '2024-04-11'), (3, 134, 10065, 23, 5, '2023-04-11'), (6, 164, 10000, 23, 9, '2024-03-11'), (5, 184, 10000, 23, 8, '2024-03-11'), (2, 234, 10450, 15, 8, '2024-04-12');

insert into Bills (BillName, Paid, MemberID, Amount)
values ('membership', true, 1, 10), 
('membership', false, 2, 10),
('membership', true, 3, 10),
('membership', true, 4, 10),
('membership', false, 5, 10),
('membership', true, 6, 10),
('membership', true, 7, 10),
('membership', false, 8, 10),
('membership', true, 9, 10),
('personal training', false, 1, 15), 
('personal training', true, 3, 15), 
('personal training', false, 4, 15), 
('personal training', true, 2, 15), 
('personal training', true, 5, 15), 
('personal training', false, 6, 15), 
('fitness class', true, 1, 20), 
('fitness class', false, 2, 20), 
('fitness class', false, 4, 20), 
('fitness class', true, 1, 20);

insert into Trainer (TrainerID, TimeSlotID, Available)
values (1, 1, true), (1, 2, false), (1, 3, true), (1, 4, true), (1, 5, false), (1, 6, true),
(2, 7, true), (2, 8, false), (2, 9, true), (2, 10, true), (2, 11, false), (2, 12, true),
(3, 13, true), (3, 14, false), (3, 15, true), (3, 16, false), (3, 17, true), (3, 18, true);

insert into Member_Trainer_Session (TrainerID, MemberID, TimeSlotID)
values (1,1,2), (1, 3, 5), (2, 4, 8), (2, 2, 11), (3, 5, 14), (3,6,16);


