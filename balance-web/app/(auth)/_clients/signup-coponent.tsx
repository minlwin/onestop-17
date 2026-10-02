'use client'

import FormsInput from "@/components/forms/forms-input"
import { Button } from "@/components/ui/button"
import { SignUpForm, signUpSchema } from "@/lib/forms/auth.schema"
import { zodResolver } from "@hookform/resolvers/zod"
import { LogIn, UserPlus } from "lucide-react"
import Link from "next/link"
import { useForm } from "react-hook-form"

export default function SignUpComponent() {

    const form = useForm({
        resolver: zodResolver(signUpSchema),
        defaultValues: {
            name: '',
            email: ''
        }
    })

    function signUp(form : SignUpForm) {
        console.log(form)
    }

    return (
        <form onSubmit={form.handleSubmit(signUp)} className="space-y-4">
            <FormsInput control={form.control} name="name" label="Name" />
            <FormsInput control={form.control} name="email" label="Email" type="email" />

            <nav className="flex items-center gap-2">
                <Button type="submit">
                    <UserPlus /> Sign Up
                </Button>

                <Button render={<Link href={'/signin'} />} nativeButton={false} variant={'link'}>
                    If you have account Sign In
                </Button>
            </nav>
        </form>
    )
}