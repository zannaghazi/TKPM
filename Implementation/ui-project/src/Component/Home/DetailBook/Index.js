import React, {useEffect} from 'react';

function Index(props) {
    useEffect(() => {
        console.log("1234", props.match.params.id);
      });
    return (
        <div>
            
        </div>
    );
}

export default Index;