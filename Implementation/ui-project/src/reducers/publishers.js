import * as types from '../constants/ActionType';
var initialState = [];

var myReducer = (state = initialState, action) => {
    switch (action.type) {
        case types.SET_LIST_PUBLISHER:
            state = action.list;
            return [...state];
        case types.ADD_PUBLISHER:
            return [...state, action.pulisher];
        case types.EDIT_PUBLISHER:
            let foundIndex = state.findIndex(x => x.id == action.publisher.id);
            let temp = [...state];
            temp[foundIndex] = action.publisher;
            return [...temp];
        case types.DELETE_PUBLISHER:
            console.log("state in delete book", state);
            console.log("item in delete book", action.item)
            const filteredItems = state.filter(function (item) {
                return item !== action.item
            })
            console.log("delete book", filteredItems);
            return [...filteredItems];
        default:
            return state;

    }
    return state;
}

export default myReducer;