import { URL_API } from "~/config";
const { createSlice, createAsyncThunk } = require("@reduxjs/toolkit");
const  initialState = {
    isLoading: false,
    msg:'',
}
// HANDLE ADD MEDIA TO DB
const getListMedia = createAsyncThunk('getListMedia',async(body)=> {
    try {
        const res = await fetch(URL_API + 'api/v1/media', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(body),
        });
        const data = await res.json();
        return data;
    } catch (error) {
        console.error('Error fetching data:', error);
        throw error;
    }
});
// HANDLE ADD MEDIA TO DB
const addMedia = createAsyncThunk('addMedia',async(body)=> {
    try {
        const res = await fetch(URL_API + 'api/v1/media/add', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(body),
        });
        const data = await res.json();
        return data;
    } catch (error) {
        console.error('Error fetching data:', error);
        throw error;
    }
});

const friendSlice = createSlice({
    name: "media",
    initialState,
    reducers: {
    },
    extraReducers : (builder) => {
        // ================= ADD FRIENDS =================
        builder.addCase(addMedia.pending,(state,action) => {
            state.isLoading = true;
        });
        builder.addCase(addMedia.fulfilled,(state,action) => {
            state.isLoading = false;
        });
        builder.addCase(addMedia.rejected,(state,action) => {
            state.isLoading = true;
        });
    }
});
export default friendSlice.reducer;
export {
    // ADD FRIENDS
    addMedia,
    // GET LIST MEDIA
    getListMedia
};