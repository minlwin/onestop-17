import z from "zod";

export const signInSchema = z.object({
    email: z.email("Enter a valid email").nonempty("Please enter your email"),
    password: z.string().nonempty("Please enter your password")
})

export type SignInForm = z.infer<typeof signInSchema>

export const signUpSchema = z.object({
    name : z.string().nonempty("Please enter your name"),
    email: z.email("Enter a valid email").nonempty("Please enter your email"),
})

export type SignUpForm = z.infer<typeof signUpSchema>

export const forgotPasswordSchema = z.object({
    email: z.email("Enter a valid email").nonempty("Please enter your email"),
})

export type ForgotPasswordForm = z.infer<typeof forgotPasswordSchema>

export const resetPasswordSchema = z.object({
    optCode: z.string().nonempty("Please enter OTP Code"),
    password: z.string().nonempty("Please enter your password")
})

export type ResetPasswordForm = z.infer<typeof resetPasswordSchema>

export const activationSchema = z.object({
    optCode: z.string().nonempty("Please enter OTP Code"),
    password: z.string().nonempty("Please enter your password")
})

export type ActivationForm = z.infer<typeof activationSchema>
