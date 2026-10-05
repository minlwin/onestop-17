'use client'

import FormsInput from "@/components/forms/forms-input"
import { Button } from "@/components/ui/button"
import { Field, FieldError, FieldLabel } from "@/components/ui/field"
import { Input } from "@/components/ui/input"
import { SignInForm, signInSchema } from "@/lib/types/anonymous/auth.schema"
import { zodResolver } from "@hookform/resolvers/zod"
import { LogIn, UserPlus } from "lucide-react"
import Link from "next/link"
import { Controller, useForm } from "react-hook-form"

export default function SignInComponent() {

    const form = useForm({
        resolver: zodResolver(signInSchema),
        defaultValues: {
            email: '',
            password: ''
        }
    })

    function signIn(form: SignInForm) {
        console.log(form)
    }

    return (
        <form onSubmit={form.handleSubmit(signIn)} className="space-y-4">

            <FormsInput control={form.control} name="email" type="email" label="Email" />
            <FormsInput control={form.control} name="password" type="password" label="Password" action={
                <Button nativeButton={false} variant={'link'} render={
                    <Link href={'/password/forgot'}>Forgot your password?</Link>
                } />
            } />

            <nav className="flex items-center gap-2">
                <Button type="submit">
                    <LogIn /> Sign In
                </Button>

                <Button render={<Link href={'/signup'} />} variant={'link'} nativeButton={false}>
                    If you have not account, please sign up.
                </Button>
             </nav>
        </form>
    )
}