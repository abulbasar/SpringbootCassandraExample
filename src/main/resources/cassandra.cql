create table if not exists demo.quiz(
    quiz_id timeuuid,
    name text,
    topics list<text>
) ;