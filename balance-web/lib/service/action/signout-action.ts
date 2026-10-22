'use server'

import * as store from "@/lib/store/auth.store"
import { redirect } from "next/navigation"

export async function signOut() {
    await store.signOut()
    redirect("/signin")
} 