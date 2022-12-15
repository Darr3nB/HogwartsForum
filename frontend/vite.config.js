import {defineConfig} from 'vite'
import react from '@vitejs/plugin-react'

// https://vitejs.dev/config/
export default defineConfig({
    plugins: [react()],
    server: {
        proxy: {
            '/api': 'http://localhost:8080',
            '/user': 'http://localhost:8080',
            '/question': 'http://localhost:8080'
        }
    }
})
