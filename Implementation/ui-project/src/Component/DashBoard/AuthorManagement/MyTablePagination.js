import React, {useState, useEffect} from 'react';
import {Pagination} from 'react-bootstrap';

function MyTablePagination(props) {
    const [active, setActive] = useState(2);
    const [items, setItems] = useState([]);

    for (let number = 1; number <= 5; number++) {
      items.push(
        <Pagination.Item key={number} active={number === active}>
          {number}
        </Pagination.Item>,
      );
    }

    return (
        <div>
            <Pagination>{items}</Pagination>
        </div>
    );
}

export default MyTablePagination;