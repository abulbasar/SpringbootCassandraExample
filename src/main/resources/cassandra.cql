create table if not exists demo.quiz(
    quiz_id timeuuid,
    name text,
    topics list<text>
) ;



create type if not exists Option(
    serial      text,
    name        text,
    answer      boolean
);

create table if not exists user( 
    user_id     uuid primary key,
    name        text
    email       text
);
    
    
create table if not exists  question(
    quiz_id          timeuuid,
    question_id      uuid,
    name             text,
    difficulty       text,
    type             text,
    options          list<Option>   
    primary key (quiz_id, question_id)
);
    
create table if not exists scorecards_by_user(
    user_id          uuid,
    quiz_id          uuid,
    username         text (static),
    quiz_name        text (static),
    questions        map<uuid,double>,
    total_score      double,
    total_time       int,
    start_time       timestamp,
    end_time         timestamp,
    status           text,
    primary (user_id, start_time, quiz_id)
);
    
    
create table if not exists attempts_by_user(   
    start_date          timestamp,
    user_id             uuid,
    quiz_id             uuid,
    question_id         uuid,
    selected_answers    list<text>, 
    time                int,
    review              boolean,
    score               double,
    primary key((user_id, quiz_id), start_date, question_id)
);
