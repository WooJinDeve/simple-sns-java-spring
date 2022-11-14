package com.sns.sns.util;

import java.util.List;

public record PageCursor<T> (
        CursorRequest nextCursorRequest,
        List<T> request
){}
