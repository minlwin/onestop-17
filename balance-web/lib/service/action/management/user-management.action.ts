"use server"

import { RetirementForm, UserForm, UserSearch } from "@/lib/types/management/user.model";
import * as client from "@/lib/service/client/management/user-rest.client"
import { redirect } from "next/navigation";

export async function search(form : UserSearch) {
    return await client.search(form)
}

export async function create(form : UserForm) {
    const response = await client.create(form)
    redirect(`/management/users/${response.result}`)
}

export async function updateRetirement(id : string, form : RetirementForm)  {
    const response = await client.updateRetirement(id, form)
    redirect(`/management/users/${response.result}`)
}
